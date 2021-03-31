package com.project.cocktailapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CocktailAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocktailAppApplication.class, args);
    }

}
