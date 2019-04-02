package ru.aaxee.homework2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableMongoRepositories
@EnableTransactionManagement
@SpringBootApplication
public class Homework2Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework2Application.class, args);
    }

}

