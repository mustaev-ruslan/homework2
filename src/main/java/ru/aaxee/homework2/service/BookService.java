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

import static com.google.common.collect.Sets.difference;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    public Book add(String name, String authorsStringList, String genresStringList) throws LibraryException {
        if (name == null) {
            throw new LibraryException("Name of book is required");
        }
        Optional<Book> existingBook = bookRepository.findByName(name);
        if (existingBook.isPresent()) {
            throw new LibraryException("Book with name " + name + " already exist");
        }
        Book blankBook = bookRepository.addBook(name);
        Long bookId = blankBook.getId();

        addAuthors(bookId, authorsStringList);
        addGenres(bookId, genresStringList);

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new LibraryException("Fail to add " + name);
        }
        return optionalBook.get();
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
        Book existingBook = optionalExistingBook.get();
        bookRepository.updateName(bookId, name);

        updateAuthors(existingBook, authorsStringList);
        updateGenres(existingBook, genresStringList);

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

    private void addAuthors(Long bookId, String authorsStringList) throws LibraryException {
        Set<String> authorsNames = split(authorsStringList);
        Set<Author> authors = addMissingAuthors(authorsNames);
        for (Author author : authors) {
            bookRepository.addAuthor(bookId, author.getId());
        }
    }

    private void addGenres(Long bookId, String genresStringList) throws LibraryException {
        Set<String> genresNames = split(genresStringList);
        Set<Genre> genres = addMissingGenres(genresNames);
        for (Genre genre : genres) {
            bookRepository.addGenre(bookId, genre.getId());
        }
    }

    private void updateGenres(Book existingBook, String genresStringList) throws LibraryException {
        Long bookId = existingBook.getId();
        Set<String> genresNames = split(genresStringList);
        Set<Genre> updatedGenres = addMissingGenres(genresNames);
        Set<Genre> addedGenres = difference(updatedGenres, existingBook.getGenres());
        Set<Genre> removedGenres = difference(existingBook.getGenres(), updatedGenres);
        for (Genre addedGenre : addedGenres) {
            bookRepository.addGenre(bookId, addedGenre.getId());
        }
        for (Genre removedGenre : removedGenres) {
            bookRepository.removeGenre(bookId, removedGenre.getId());
        }
    }

    private void updateAuthors(Book existingBook, String authorsStringList) throws LibraryException {
        Long bookId = existingBook.getId();
        Set<String> authorsNames = split(authorsStringList);
        Set<Author> updatedAuthors = addMissingAuthors(authorsNames);
        Set<Author> addedAuthors = difference(updatedAuthors, existingBook.getAuthors());
        Set<Author> removedAuthors = difference(existingBook.getAuthors(), updatedAuthors);
        for (Author addedAuthor : addedAuthors) {
            bookRepository.addAuthor(bookId, addedAuthor.getId());
        }
        for (Author removedAuthor : removedAuthors) {
            bookRepository.removeAuthor(bookId, removedAuthor.getId());
        }
    }

    private Set<Author> addMissingAuthors(Collection<String> authorsNames) throws LibraryException {
        Set<Author> authors = new HashSet<>();
        for (String authorName : authorsNames) {
            Optional<Author> optionalAuthor = authorService.findByName(authorName);
            Author author;
            if (!optionalAuthor.isPresent()) {
                author = authorService.add(authorName);
            } else {
                author = optionalAuthor.get();
            }
            authors.add(author);
        }
        return authors;
    }

    private Set<Genre> addMissingGenres(Collection<String> genresNames) throws LibraryException {
        Set<Genre> genres = new HashSet<>();
        for (String genreName : genresNames) {
            Optional<Genre> optionalGenre = genreService.findByName(genreName);
            Genre genre;
            if (!optionalGenre.isPresent()) {
                genre = genreService.add(genreName);
            } else {
                genre = optionalGenre.get();
            }
            genres.add(genre);
        }
        return genres;
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
