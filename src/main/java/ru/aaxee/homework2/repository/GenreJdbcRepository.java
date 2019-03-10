package ru.aaxee.homework2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.aaxee.homework2.domain.Genre;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.ImmutableMap.of;

@RequiredArgsConstructor
@Repository
public class GenreJdbcRepository implements GenreRepository {

    private final NamedParameterJdbcOperations jdbc;

    private final static RowMapper<Genre> mapRow = (rs, rowNum) ->
            new Genre(rs.getLong("id"), rs.getString("name"));

    @Override
    public void addGenre(String name) {
        jdbc.update("INSERT INTO genre (name) VALUES (:name)", of("name", name));
    }

    @Override
    public Optional<Genre> findById(Long id) {
        Genre genre;
        try {
            genre = jdbc.queryForObject("SELECT * FROM genre WHERE id=:id", of("id", id), mapRow);
        } catch (EmptyResultDataAccessException ignore) {
            return Optional.empty();
        }
        return Optional.ofNullable(genre);
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query("SELECT * FROM genre", mapRow);
    }

    @Override
    public void updateGenre(Long id, String name) {
        jdbc.update("UPDATE genre SET name = :name WHERE id = :id", of("id", id, "name", name));
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("DELETE FROM genre WHERE id=:id", of("id", id));
    }

    @Override
    public Optional<Genre> findByName(String name) {
        Genre genre;
        try {
            genre = jdbc.queryForObject("SELECT * FROM genre WHERE name=:name", of("name", name), mapRow);
        } catch (EmptyResultDataAccessException ignore) {
            return Optional.empty();
        }
        return Optional.ofNullable(genre);
    }
}
