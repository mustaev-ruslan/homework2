package ru.aaxee.homework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aaxee.homework2.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);

}
