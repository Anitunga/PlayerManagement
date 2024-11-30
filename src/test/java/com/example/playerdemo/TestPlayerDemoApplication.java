package com.example.playerdemo;

import org.springframework.boot.SpringApplication;

public class TestPlayerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.from(PlayerDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
