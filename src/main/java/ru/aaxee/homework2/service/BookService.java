package ru.aaxee.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aaxee.homework2.collections.MathSet;
import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.domain.Genre;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.repository.BookRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book add(String name, String authorsStringList, String genresStringList) throws LibraryException {
        if (name == null) {
            throw new LibraryException("Name of book is required");
        }
        Optional<Book> existingBook = bookRepository.findByName(name);
        if (existingBook.isPresent()) {
            throw new LibraryException("Book with name " + name + " already exist");
        }
        Book book = new Book();
        book.setName(name);
        setAuthorsFromString(book, authorsStringList);
        setGenresFromString(book, genresStringList);
        bookRepository.addBook(book);
        return book;
    }

    public List<Book> find(Long id, String name, String author, String genre) {
        MathSet<Long> idSet = MathSet.all();

        if (id != null) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (!optionalBook.isPresent()) {
                idSet.nothing();
            } else {
                idSet.filter(optionalBook.get().getId());
            }
        }

        if (name != null && idSet.notEmpty()) {
            Optional<Book> optionalBook = bookRepository.findByName(name);
            if (!optionalBook.isPresent()) {
                idSet.nothing();
            } else {
                idSet.filter(optionalBook.get().getId());
            }
        }

        if (author != null && idSet.notEmpty()) {
            List<Book> booksByAuthor = bookRepository.findByAuthor(author);
            idSet.filter(booksByAuthor.stream().map(Book::getId).collect(toSet()));
        }

        if (genre != null && idSet.notEmpty()) {
            List<Book> booksByGenre = bookRepository.findByGenre(genre);
            idSet.filter(booksByGenre.stream().map(Book::getId).collect(toSet()));
        }

        List<Book> books;
        if (idSet.isAll()) {
            books = bookRepository.findAll();
        } else if (idSet.notEmpty()) {
            books = bookRepository.findByIds(idSet.toSet());
        } else {
            books = emptyList();
        }

        return books;
    }

    public Book update(Long bookId, String name, String authorsStringList, String genresStringList) throws LibraryException {
        Optional<Book> optionalExistingBook = bookRepository.findById(bookId);
        if (!optionalExistingBook.isPresent()) {
            throw new LibraryException("Book with id " + bookId + " not exist");
        }

        Book book = new Book();
        book.setId(bookId);
        book.setName(name);
        setAuthorsFromString(book, authorsStringList);
        setGenresFromString(book, genresStringList);
        bookRepository.updateBook(book);

        Optional<Book> updatedBook = bookRepository.findById(bookId);
        if (!updatedBook.isPresent()) {
            throw new LibraryException("Fail to update " + updatedBook);
        }
        return updatedBook.get();
    }

    public void delete(Long id) throws LibraryException {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (!existingBook.isPresent()) {
            throw new LibraryException("Book with id " + id + " not exist");
        }
        bookRepository.deleteById(id);
    }


    private void setGenresFromString(Book book, String genresStringList) {
        book.setGenres(stringToObjects(genresStringList, genreName -> {
            Genre genre = new Genre();
            genre.setName(genreName);
            return genre;
        }));
    }

    private void setAuthorsFromString(Book book, String authorsStringList) {
        book.setAuthors(stringToObjects(authorsStringList, authorName -> {
            Author author = new Author();
            author.setName(authorName);
            return author;
        }));
    }

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
