package com.jarvis.common.bdb;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.*;

import java.io.File;

/**
 * BDB数据库环境
 *
 * @author taoze
 * @version 1.0
 * @date 4/6/21 9:32 PM
 */
public class BdbEnvironmentConfig extends Environment {
    StoredClassCatalog classCatalog;
    Database classCatalogDB;

    /**
     * Constructor
     *
     * @param envHome   数据库环境目录
     * @param envConfig config options  数据库换纪念馆配置
     * @throws DatabaseException
     */
    public BdbEnvironmentConfig(File envHome, EnvironmentConfig envConfig) throws DatabaseException {
        super(envHome, envConfig);
    }

    /**
     * 返回StoredClassCatalog
     *
     * @return the cached class catalog
     */
    public StoredClassCatalog getClassCatalog() {
        if (classCatalog == null) {
            DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setAllowCreate(true);
            try {
                classCatalogDB = openDatabase(null, "classCatalog", dbConfig);
                classCatalog = new StoredClassCatalog(classCatalogDB);
            } catch (DatabaseException e) {
                // TODO Auto-generated catch block
            }
        }
        return classCatalog;
    }

    @Override
    public synchronized void close() throws DatabaseException {
        if (classCatalogDB != null) {
            classCatalogDB.close();
        }
        super.close();
    }
}