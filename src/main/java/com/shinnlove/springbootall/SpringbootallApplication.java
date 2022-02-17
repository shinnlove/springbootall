package com.shinnlove.springbootall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.shinnlove.springbootall",
                                            "com.shinnlove.springbootall.service.handlers",
                                            "com.bilibili.universal" })
public class SpringbootallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootallApplication.class, args);
    }

}
