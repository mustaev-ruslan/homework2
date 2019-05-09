package ru.aaxee.homework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.security.User;
import ru.aaxee.homework2.security.UserRepository;
import ru.aaxee.homework2.service.BookService;

import javax.annotation.PostConstruct;

@EnableTransactionManagement
@SpringBootApplication
public class Homework2Application {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Homework2Application.class, args);

    }

    @PostConstruct
    public void init() {
        try {
            bookService.add("лукоморье", "Пушкин,Лермонтов", "Комедия,Трагедия");
            bookService.add("Пес барбос", "Дятлов", "Ужасы");

            User admin = new User();
            admin.setId(1L);
            admin.setUsername("admin");
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
            String encode = bCryptPasswordEncoder.encode("password");
            admin.setPassword(encode);
            userRepository.save(admin);

        } catch (LibraryException e) {
            e.printStackTrace();
        }
    }

}

