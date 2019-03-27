package ru.aaxee.homework2.repository;

import ru.aaxee.homework2.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    void addAuthor(String name);

    Optional<Author> findById(Long id);

    List<Author> findAll();

    void updateAuthor(Long id, String name);

    void deleteById(Long id);

    Optional<Author> findByName(String name);
}
