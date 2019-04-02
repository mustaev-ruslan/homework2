package ru.aaxee.homework2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.aaxee.homework2.domain.Book;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringDataRepositoryMethodParametersInspection")
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByName(String name);

    List<Book> findByAuthorsName(String authorName);

    List<Book> findByGenresName(String genreName);
}
