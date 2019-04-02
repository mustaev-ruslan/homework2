package ru.aaxee.homework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aaxee.homework2.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);

}
