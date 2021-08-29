package com.y.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.y.community.mapper")
public class CommuctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommuctionApplication.class, args);
    }

}
