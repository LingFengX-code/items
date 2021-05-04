package com.heifeng.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HeifengUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeifengUploadApplication.class, args);
    }
}
