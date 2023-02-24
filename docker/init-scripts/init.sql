-- 创建数据库
CREATE DATABASE easy-iot;

-- 连接到数据库
\c easy-iot;

-- 创建设备表
CREATE TABLE easy_table_device (
                                   id VARCHAR(64) PRIMARY KEY,
                                   name VARCHAR(32),
                                   created_at TIMESTAMP DEFAULT now(),
                                   updated_at TIMESTAMP DEFAULT now(),
                                   deleted BOOLEAN DEFAULT false,
                                   status VARCHAR(16)
);
