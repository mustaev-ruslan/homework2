package ru.aaxee.homework2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

@RequiredArgsConstructor
@Repository
public class BookJdbcRepository implements BookRepository {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final NamedParameterJdbcOperations jdbc;

    private final static RowMapper<Book> bookMapRow = (rs, rowNum) ->
            new Book(rs.getLong("id"), rs.getString("name"));

    private final static RowMapper<Author> authorsMapRow = (rs, rowNum) ->
            new Author(rs.getLong("id"), rs.getString("name"));

    private final static RowMapper<Genre> genresMapRow = (rs, rowNum) ->
            new Genre(rs.getLong("id"), rs.getString("name"));

    @Override
    public Optional<Book> findById(Long bookId) {
        Book blankBook;
        try {
            blankBook = jdbc.queryForObject("SELECT * FROM book WHERE id=:bookId", of("bookId", bookId), bookMapRow);
            assert blankBook != null;
        } catch (EmptyResultDataAccessException ignore) {
            return Optional.empty();
        }
        List<Author> authors = getAuthors(bookId);
        List<Genre> genres = getGenres(bookId);
        return Optional.of(new Book(bookId, blankBook.getName(), newHashSet(authors), newHashSet(genres)));
    }

    private List<Genre> getGenres(Long bookId) {
        return jdbc.query(
                    "SELECT genre.id AS id, genre.name AS name " +
                            "FROM genre JOIN book_genre ON genre.id = book_genre.genre_id " +
                            "WHERE book_genre.book_id=:bookId",
                    of("bookId", bookId), genresMapRow);
    }

    private List<Author> getAuthors(Long bookId) {
        return jdbc.query(
                    "SELECT author.id AS id, author.name AS name " +
                            "FROM author JOIN book_author ON author.id = book_author.author_id " +
                            "WHERE book_author.book_id=:bookId",
                    of("bookId", bookId), authorsMapRow);
    }

    @Override
    public List<Book> findAll() {
        List<Long> bookIds = jdbc.queryForList("SELECT id FROM book", emptyMap(), Long.class);
        return bookIds.parallelStream().map(bookId -> findById(bookId).get()).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long bookId) {
        jdbc.update("DELETE FROM book WHERE id=:bookId", of("bookId", bookId));
    }

    @Override
    public Optional<Book> findByName(String name) {
        Book blankBook;
        try {
            blankBook = jdbc.queryForObject("SELECT * FROM book WHERE name=:name", of("name", name), bookMapRow);
            assert blankBook != null;
        } catch (EmptyResultDataAccessException ignore) {
            return Optional.empty();
        }
        Long bookId = blankBook.getId();
        List<Author> authors = getAuthors(bookId);
        List<Genre> genres = getGenres(bookId);
        return Optional.of(new Book(bookId, blankBook.getName(), newHashSet(authors), newHashSet(genres)));
    }

    @Override
    public Book addBook(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("INSERT INTO book (name) VALUES (:name)", new MapSqlParameterSource(of("name", name)), keyHolder);
        Long bookId = (Long) keyHolder.getKey();
        return new Book(bookId, name);
    }

    @Override
    public void addAuthor(Long bookId, Long authorId) {
        jdbc.update("INSERT INTO book_author (book_id, author_id) VALUES (:bookId, :authorId)", of("bookId", bookId, "authorId", authorId));
    }

    @Override
    public void addGenre(Long bookId, Long genreId) {
        jdbc.update("INSERT INTO book_genre (book_id, genre_id) VALUES (:bookId, :genreId)", of("bookId", bookId, "genreId", genreId));
    }

    @Override
    public List<Book> findByIds(Set<Long> idSet) {
        List<Book> books = new ArrayList<>();
        idSet.parallelStream().forEach(bookId -> findById(bookId).ifPresent(books::add));
        return books;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Optional<Author> optionalAuthor = authorRepository.findByName(author);
        if (optionalAuthor.isPresent()) {
            Long authorId = optionalAuthor.get().getId();
            List<Long> bookIds = jdbc.queryForList("SELECT book_id FROM book_author WHERE author_id = :authorId", of("authorId", authorId), Long.class);
            return findByIds(newHashSet(bookIds));
        } else {
            return emptyList();
        }
    }

    @Override
    public List<Book> findByGenre(String genre) {
        Optional<Genre> optionalGenre = genreRepository.findByName(genre);
        if (optionalGenre.isPresent()) {
            Long genreId = optionalGenre.get().getId();
            List<Long> bookIds = jdbc.queryForList("SELECT book_id FROM book_genre WHERE genre_id = :genreId", of("genreId", genreId), Long.class);
            return findByIds(newHashSet(bookIds));
        } else {
            return emptyList();
        }
    }

    @Override
    public void updateName(Long bookId, String name) {
        jdbc.update("UPDATE book SET name = :name WHERE id = :id", of("id", bookId, "name", name));
    }

    @Override
    public void removeAuthor(Long bookId, Long authorId) {
        jdbc.update("DELETE FROM book_author WHERE book_id = :bookId AND author_id = :authorId", of("bookId", bookId, "authorId", authorId));
    }

    @Override
    public void removeGenre(Long bookId, Long genreId) {
        jdbc.update("DELETE FROM book_genre WHERE book_id = :bookId AND genre_id = :genreId", of("bookId", bookId, "genreId", genreId));
    }
}
