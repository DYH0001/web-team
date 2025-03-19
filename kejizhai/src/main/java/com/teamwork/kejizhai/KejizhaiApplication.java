package com.teamwork.kejizhai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.teamwork.kejizhai")
@EntityScan("com.teamwork.kejizhai.bean")
public class KejizhaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KejizhaiApplication.class, args);
    }
}
