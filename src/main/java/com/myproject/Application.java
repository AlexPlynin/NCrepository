package com.myproject;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan("\\myproject\\init")
//@RequiredArgsConstructor
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
