package ru.aaxee.homework2.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Book {

    @Id
    private Long id;

    private String name;
//
//    private Set<Author> authors;
//
//    private Set<Genre> genres;
}
