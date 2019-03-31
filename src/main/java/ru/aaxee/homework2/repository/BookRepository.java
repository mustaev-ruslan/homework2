package ru.aaxee.homework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aaxee.homework2.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);

    List<Book> findByAuthorsName(String authorName);

    List<Book> findByGenresName(String genreName);
}
