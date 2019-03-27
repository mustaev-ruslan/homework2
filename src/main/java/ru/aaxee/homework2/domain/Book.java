package ru.aaxee.homework2.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Set;

import static java.util.Collections.emptySet;

@Value
@AllArgsConstructor
public class Book {

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
        this.authors = emptySet();
        this.genres = emptySet();
    }

    private final Long id;
    private final String name;
    private final Set<Author> authors;
    private final Set<Genre> genres;
}
