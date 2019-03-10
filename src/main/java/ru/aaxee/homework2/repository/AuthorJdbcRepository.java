package ru.aaxee.homework2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.aaxee.homework2.domain.Author;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.ImmutableMap.of;

@RequiredArgsConstructor
@Repository
public class AuthorJdbcRepository implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbc;

    private final static RowMapper<Author> mapRow = (rs, rowNum) ->
            new Author(rs.getLong("id"), rs.getString("name"));

    @Override
    public void addAuthor(String name) {
        jdbc.update("INSERT INTO author (name) VALUES (:name)", of("name", name));
    }

    @Override
    public Optional<Author> findById(Long id) {
        Author author;
        try {
            author = jdbc.queryForObject("SELECT * FROM author WHERE id=:id", of("id", id), mapRow);
        } catch (EmptyResultDataAccessException ignore) {
            return Optional.empty();
        }
        return Optional.ofNullable(author);
    }

    @Override
    public List<Author> findAll() {
        return jdbc.query("SELECT * FROM author", mapRow);
    }

    @Override
    public void updateAuthor(Long id, String name) {
        jdbc.update("UPDATE author SET name = :name WHERE id = :id", of("id", id, "name", name));
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("DELETE FROM author WHERE id=:id", of("id", id));
    }

    @Override
    public Optional<Author> findByName(String name) {
        Author author;
        try {
            author = jdbc.queryForObject("SELECT * FROM author WHERE name=:name", of("name", name), mapRow);
        } catch (EmptyResultDataAccessException ignore) {
            return Optional.empty();
        }
        return Optional.ofNullable(author);
    }
}
