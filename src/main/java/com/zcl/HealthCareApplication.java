package com.zcl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zcl.dao")
public class HealthCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCareApplication.class, args);
    }

}
