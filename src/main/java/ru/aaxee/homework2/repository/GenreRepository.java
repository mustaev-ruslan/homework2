package ru.aaxee.homework2.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.aaxee.homework2.domain.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {

}
