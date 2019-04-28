package ru.aaxee.homework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.BookService;

import javax.annotation.PostConstruct;

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
        try {
            bookService.add("лукоморье", "Пушкин,Лермонтов", "Комедия,Трагедия");
            bookService.add("Пес барбос", "Дятлов", "Ужасы");
        } catch (LibraryException e) {
            e.printStackTrace();
        }
    }

}

