package com.jarvis.easy.protocol.io;

import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author lixiaofei
 */
@Component
public class MinioClientManager implements MinioInterface {

    @Value("${spring.cloud.minio.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.minio.access-key}")
    private String accessKey;

    @Value("$spring.cloud.minio.secret-key{}")
    private String accessSceret;

    private MinioClient minioClient;


    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, accessSceret).build();
    }


    @Override
    public ObjectWriteResponse putObject(PutObjectArgs args) {
        return null;
    }

    @Override
    public GetObjectResponse getObject(GetObjectArgs args) {
        return null;
    }

    @Override
    public void removeObject(RemoveObjectArgs args) {

    }

    @Override
    public Iterable<Result<Item>> listObjects(ListObjectsArgs args) {
        return null;
    }

    @Override
    public List<Bucket> listBuckets() {
        return null;
    }
}
