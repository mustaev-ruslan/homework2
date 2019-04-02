package ru.aaxee.homework2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.aaxee.homework2.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Optional<Genre> findByName(String name);

}
