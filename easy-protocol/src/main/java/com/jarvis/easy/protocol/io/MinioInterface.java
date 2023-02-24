package com.jarvis.easy.protocol.io;

import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

import java.util.List;

public interface MinioInterface {
    /**
     * 写入对象
     *
     * @param args
     * @return
     */
    ObjectWriteResponse putObject(PutObjectArgs args);

    /**
     * 获取对象
     *
     * @param args
     * @return
     */
    GetObjectResponse getObject(GetObjectArgs args);

    /**
     * 删除对象
     *
     * @param args
     */
    void removeObject(RemoveObjectArgs args);

    /**
     * 查询对象
     *
     * @param args
     * @return
     */
    Iterable<Result<Item>> listObjects(final ListObjectsArgs args);

    /**
     * 查询bucket
     *
     * @return
     */
    List<Bucket> listBuckets();
}
