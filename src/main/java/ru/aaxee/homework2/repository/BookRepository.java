package ru.aaxee.homework2.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.aaxee.homework2.domain.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

}
