package com.jarvis.easy.common.bdb;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.je.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 持久化队列,基于BDB实现
 *
 * @author taoze
 * @version 1.0
 * @date 4/6/21 9:31 PM
 */
public class BdbPersistentQueue<E extends Serializable> extends AbstractQueue<E> implements Serializable {
    private static final long serialVersionUID = 3427799316155220967L;
    private transient BdbEnvironmentConfig dbEnv;            // 数据库环境,无需序列化
    private transient Database queueDb;             // 数据库,用于保存值,使得支持队列持久化,无需序列化
    private transient StoredMap<Long, E> queueMap;   // 持久化Map,Key为指针位置,Value为值,无需序列化
    private transient String dbDir;                 // 数据库所在目录
    private transient String dbName;                // 数据库名字
    private AtomicLong headIndex;                   // 头部指针
    private AtomicLong tailIndex;                   // 尾部指针
    private transient E peekItem = null;              // 当前获取的值

    /**
     * 构造函数,传入BDB数据库
     *
     * @param db
     * @param valueClass
     * @param classCatalog
     */
    public BdbPersistentQueue(Database db, Class<E> valueClass, StoredClassCatalog classCatalog) {
        this.queueDb = db;
        this.dbName = db.getDatabaseName();
        headIndex = new AtomicLong(0);
        tailIndex = new AtomicLong(0);
        bindDatabase(queueDb, valueClass, classCatalog);
    }

    /**
     * 构造函数,传入BDB数据库位置和名字,自己创建数据库
     *
     * @param dbDir
     * @param dbName
     * @param valueClass
     */
    public BdbPersistentQueue(String dbDir, String dbName, Class<E> valueClass) {
        headIndex = new AtomicLong(0);
        tailIndex = new AtomicLong(0);
        this.dbDir = dbDir;
        this.dbName = dbName;
        createAndBindDatabase(dbDir, dbName, valueClass);
    }

    /**
     * 绑定数据库
     *
     * @param db
     * @param valueClass
     * @param classCatalog
     */
    public void bindDatabase(Database db, Class<E> valueClass, StoredClassCatalog classCatalog) {
        EntryBinding<E> valueBinding = TupleBinding.getPrimitiveBinding(valueClass);
        if (valueBinding == null) {
            // 序列化绑定
            valueBinding = new SerialBinding<E>(classCatalog, valueClass);
        }
        queueDb = db;
        // db,key,value,allow write
        queueMap = new StoredSortedMap<Long, E>(db, TupleBinding.getPrimitiveBinding(Long.class), valueBinding, true);
    }

    /**
     * 创建以及绑定数据库
     *
     * @param dbDir
     * @param dbName
     * @param valueClass
     * @throws DatabaseNotFoundException
     * @throws DatabaseExistsException
     * @throws DatabaseException
     * @throws IllegalArgumentException
     */
    private void createAndBindDatabase(String dbDir, String dbName, Class<E> valueClass)
            throws DatabaseNotFoundException, DatabaseExistsException, DatabaseException, IllegalArgumentException {
        File envFile = null;
        EnvironmentConfig envConfig = null;
        DatabaseConfig dbConfig = null;
        Database db = null;

        try {
            // 数据库位置
            envFile = new File(dbDir);

            // 数据库环境配置
            envConfig = new EnvironmentConfig();
            envConfig.setAllowCreate(true);
            envConfig.setTransactional(false);

            // 数据库配置
            dbConfig = new DatabaseConfig();
            dbConfig.setAllowCreate(true);

            // 创建环境
            dbEnv = new BdbEnvironmentConfig(envFile, envConfig);
            // 打开数据库
            db = dbEnv.openDatabase(null, dbName, dbConfig);
            // 绑定数据库
            bindDatabase(db, valueClass, dbEnv.getClassCatalog());

        } catch (DatabaseNotFoundException e) {
            throw e;
        } catch (DatabaseExistsException e) {
            throw e;
        } catch (DatabaseException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    public List<String> selectAll() {
        Cursor cursor = null;
        cursor = queueDb.openCursor(null, null);
        DatabaseEntry theKey = null;
        DatabaseEntry theData = null;
        theKey = new DatabaseEntry();
        theData = new DatabaseEntry();
        ArrayList<String> list = new ArrayList<>();
        while (cursor.getNext(theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
            list.add(new String(theData.getData()));
            System.out.println(new String(theData.getData()));
        }
        cursor.close();
        return list;
    }

    /**
     * 值遍历器
     */
    @Override
    public Iterator<E> iterator() {
        return queueMap.values().iterator();
    }

    /**
     * 大小
     */
    @Override
    public int size() {
        synchronized (tailIndex) {
            synchronized (headIndex) {
                return (int) (tailIndex.get() - headIndex.get());
            }
        }
    }

    /**
     * 插入值
     */
    @Override
    public boolean offer(E e) {
        synchronized (tailIndex) {
            queueMap.put(tailIndex.getAndIncrement(), e);   // 从尾部插入
            dbEnv.sync();
        }
        return true;
    }

    public boolean init(E e) {
        synchronized (tailIndex) {
            queueMap.put(tailIndex.getAndIncrement(), e);   // 从尾部插入
        }
        return true;
    }

    /**
     * 获取值,从头部获取
     */
    @Override
    public E peek() {
        synchronized (headIndex) {
            if (peekItem != null) {
                return peekItem;
            }
            E headItem = null;
            while (headItem == null && headIndex.get() < tailIndex.get()) { // 没有超出范围
                headItem = queueMap.get(headIndex.get());
                if (headItem != null) {
                    peekItem = headItem;
                    continue;
                }
                headIndex.incrementAndGet();    // 头部指针后移
            }
            return headItem;
        }
    }

    /**
     * 移出元素,移出头部元素
     */
    @Override
    public E poll() {
        synchronized (headIndex) {
            E headItem = peek();
            if (headItem != null) {

                queueMap.remove(headIndex.getAndIncrement());
                peekItem = null;
                dbEnv.sync();
                return headItem;
            }
        }
        return null;
    }

    /**
     * 同步数据到磁盘当中，相当于让数据实时持久化
     *
     * @return
     */
    public boolean sync() {
        if (dbEnv != null) {
            try {
                dbEnv.sync();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     * 关闭,也就是关闭所是用的BDB数据库但不关闭数据库环境
     */
    public void close() {
        try {
            if (queueDb != null) {
                queueDb.sync();
                queueDb.close();
            }
        } catch (DatabaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 清理,会清空数据库,并且删掉数据库所在目录,慎用.如果想保留数据,请调用close()
     */
    @Override
    public void clear() {
        try {
            close();
            if (dbEnv != null && queueDb != null) {
                dbEnv.removeDatabase(null, dbName == null ? queueDb.getDatabaseName() : dbName);
                dbEnv.close();
            }
        } catch (DatabaseNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (this.dbDir != null) {
                    FileUtils.deleteDirectory(new File(this.dbDir));
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
