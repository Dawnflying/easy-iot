package com.jarvis.easy.protocol.io;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lixiaofei
 */
@Component
public class MinioClientManager {

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



}
