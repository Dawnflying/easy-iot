package com.jarvis.easy.message.queue;

import com.google.common.eventbus.EventBus;
import com.jarvis.easy.common.bdb.BdbPersistentQueue;
import com.jarvis.easy.common.entity.MessageData;
import com.jarvis.easy.common.utils.GsonUtils;
import com.jarvis.easy.message.entity.PersistItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * BdbQueue
 *
 * @author taozeR
 * @version 1.0
 * @date 4/7/21 5:48 PM
 */
@Slf4j
@Service
@EnableScheduling
public class MessageDataQueueWorker {

    Queue<String> persistentQueue;

    @Value("${bdb.queue.dbDir:rdb/data/http}")
    private String dbDir;
    @Value("${bdb.queue.databaseName:message}")
    private String databaseName;

    @Value("${bdb.queue.drainMaxSize:100}")
    private Integer drainMaxSize;

    @Resource
    private EventBus messageEventBus;

    @PostConstruct
    public void init() {
        File file = new File(dbDir);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        persistentQueue = new BdbPersistentQueue(dbDir, databaseName, String.class);
    }

    /**
     * 填充队列
     *
     * @param topic
     * @param msg
     */
    public void addQueue(String topic, String tags, MessageData msg) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("topic", topic);
        map.put("tags", tags);
        map.put("msg", msg);
        String queueStr = GsonUtils.toString(map);
        persistentQueue.offer(queueStr);
    }

    /**
     * 排放队列
     */
    @Scheduled(initialDelay = 20000, fixedDelay = 1000)
    public void drainQueue() {
        int count = 0;
        while (persistentQueue.iterator().hasNext()) {
            try {
                if (count > drainMaxSize) {
                    return;
                }
                String queueStr = persistentQueue.poll();
                PersistItem item = GsonUtils.parseObject(queueStr, PersistItem.class);
                if (item == null) {
//                    log.error("BDB 数据异常", queueStr);
                    continue;
                }

                String topic = item.getTopic();
                MessageData msg = item.getMsg();

                messageEventBus.post(msg);
            } catch (Exception e) {
//                log.error("BDB 队列 排放异常", e);
            } finally {
                count++;
            }
        }
    }

    /**
     * 获取bdb 全部消息
     *
     * @return
     */
    public List<String> selectQueueAll() {
        BdbPersistentQueue pq = new BdbPersistentQueue(dbDir, databaseName, String.class);
        return pq.selectAll();
    }
}