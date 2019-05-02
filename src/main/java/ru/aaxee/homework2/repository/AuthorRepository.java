package ru.aaxee.homework2.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.aaxee.homework2.domain.Author;

public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {

}
