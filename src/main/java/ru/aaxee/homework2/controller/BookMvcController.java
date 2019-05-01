package ru.aaxee.homework2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BookMvcController {

    @GetMapping("/")
    public String mainPage() {
        return "books";
    }
}
