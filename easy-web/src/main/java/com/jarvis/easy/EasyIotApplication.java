package com.jarvis.easy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jarvis.easy")
public class EasyIotApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyIotApplication.class, args);
    }
}
