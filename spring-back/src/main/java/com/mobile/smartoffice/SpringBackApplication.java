package com.mobile.smartoffice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mobile.smartoffice.api.mapper")
@SpringBootApplication
public class SpringBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBackApplication.class, args);
    }

}
