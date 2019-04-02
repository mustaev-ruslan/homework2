package ru.aaxee.homework2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.aaxee.homework2.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findByName(String name);

}
