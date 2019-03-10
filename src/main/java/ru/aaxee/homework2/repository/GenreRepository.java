package ru.aaxee.homework2.repository;

import ru.aaxee.homework2.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    void addGenre(String name);

    Optional<Genre> findById(Long id);

    List<Genre> findAll();

    void updateGenre(Long id, String name);

    void deleteById(Long id);

    Optional<Genre> findByName(String name);
}
