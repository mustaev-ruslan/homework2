package ru.aaxee.homework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.aaxee.homework2.service.BookService;

import javax.annotation.PostConstruct;

@EnableR2dbcRepositories
@EnableTransactionManagement
@SpringBootApplication
public class Homework2Application {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(Homework2Application.class, args);

    }

    @PostConstruct
    public void init() {
        bookService.add("лукоморье", "Пушкин,Лермонтов", "Комедия,Трагедия").block();
        bookService.add("Пес барбос", "Дятлов", "Ужасы").block();
    }

}

