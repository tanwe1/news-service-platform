package com.sdnware.news.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sdnware.news")
public class AdminBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootStrap.class, args);
    }
}
