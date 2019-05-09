package ru.aaxee.homework2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BookMvcController {

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books-page")
    public String booksPage() {
        return "books";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
