package com.panda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PandaApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(PandaApplication.class, args);
    }
}