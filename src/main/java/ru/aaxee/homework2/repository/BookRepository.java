package ru.aaxee.homework2.repository;

import ru.aaxee.homework2.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository {

    Optional<Book> findById(Long id);

    List<Book> findAll();

    void deleteById(Long id);

    Optional<Book> findByName(String name);

    Book addBook(String name);

    void addAuthor(Long bookId, Long authorId);

    void addGenre(Long bookId, Long genreId);

    List<Book> findByIds(Set<Long> idSet);

    List<Book> findByAuthor(String author);

    List<Book> findByGenre(String genre);

    void updateName(Long bookId, String name);

    void removeAuthor(Long bookId, Long authorId);

    void removeGenre(Long bookId, Long genreId);
}
