package com.heifeng.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.heifeng.item.mapper") // mapper接口的包扫描
public class HeifengItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeifengItemServiceApplication.class,args);
    }
}
