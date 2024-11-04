package com.desafioPerc.ms_main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class
 */
@SpringBootApplication
public class MsMainApplication {
    /**
     * Main method
     * @param args args
     */
    public static void main(final String[] args) {
        SpringApplication.run(MsMainApplication.class, args);
        System.out.println("App up and running!");
    }
}
