package ru.aaxee.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.repository.BookRepository;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@RequiredArgsConstructor
@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    public Mono<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> add(String name, String authorsStringList, String genresStringList) {
        Book book = new Book();
        book.setName(name);
        //setAuthorsFromString(book, authorsStringList);
        //setGenresFromString(book, genresStringList);
        return bookRepository.save(book);
    }

    public Mono<Book> update(Long bookId, String name, String authorsStringList, String genresStringList) {
        Book book = new Book();
        book.setId(bookId);
        book.setName(name);
        //setAuthorsFromString(book, authorsStringList);
        //setGenresFromString(book, genresStringList);
        return bookRepository.save(book);
    }

    public Mono<Void> delete(Long id) {
        return bookRepository.deleteById(id);
    }


//    private void setGenresFromString(Book book, String genresStringList) {
//        book.setGenres(stringToObjects(genresStringList, genreName -> {
//            Genre genre = new Genre();
//            genre.setName(genreName);
//            return genre;
//        }));
//    }

//    private void setAuthorsFromString(Book book, String authorsStringList) {
//        book.setAuthors(stringToObjects(authorsStringList, authorName -> {
//            Author author = new Author();
//            author.setName(authorName);
//            return author;
//        }));
//    }

    private <T> Set<T> stringToObjects(String stringList, Function<? super String, ? extends T> mapper) {
        return split(stringList).stream().map(mapper).collect(Collectors.toSet());
    }


    private Set<String> split(String list) {
        Set<String> strings;
        if (list == null) {
            strings = emptySet();
        } else {
            strings = newHashSet(list.split(","));
        }
        return strings;
    }
}
