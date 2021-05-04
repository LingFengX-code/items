package com.heifeng.user.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.heifeng.user.registry.mapper")
public class HeifengUserRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeifengUserRegistryApplication.class, args);
    }
}
