package com.keep.keep_backfront;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.keep.keep_backfront.dao")
public class KeepBackfrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeepBackfrontApplication.class, args);
    }

}
