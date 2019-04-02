package ru.aaxee.homework2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.aaxee.homework2.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void before() {
        authorRepository.deleteAll();
    }

    @Test
    void addAuthor() {
        String name = "Author1";
        Author author = new Author();
        author.setName(name);

        authorRepository.save(author);
        String id = author.getId();

        Optional<Author> optionalAuthorByName = authorRepository.findByName(name);
        assertThat(optionalAuthorByName).isNotEmpty();

        Optional<Author> optionalAuthorById = authorRepository.findById(id);
        assertThat(optionalAuthorById).isNotEmpty();
    }

    @Test
    void findAll() {
        String name1 = "Author1";
        Author author1 = new Author();
        author1.setName(name1);
        authorRepository.save(author1);
        String name2 = "Author2";
        Author author2 = new Author();
        author2.setName(name2);
        authorRepository.save(author2);
        String name3 = "Author3";
        Author author3 = new Author();
        author3.setName(name3);
        authorRepository.save(author3);

        List<Author> authors = authorRepository.findAll();

        assertThat(authors).hasSize(3);
    }

    @Test
    void updateAuthor() {
        String name = "Author1";
        Author author = new Author();
        author.setName(name);
        authorRepository.save(author);
        String id = author.getId();
        String newName = "New name";

        authorRepository.save(new Author(id, newName));
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get().getName()).isEqualTo(newName);
    }

    @Test
    void deleteById() {
        String name = "Author1";
        Author author = new Author();
        author.setName(name);
        authorRepository.save(author);
        String id = author.getId();

        Optional<Author> before = authorRepository.findById(id);
        assertThat(before).isPresent();

        authorRepository.deleteById(id);

        Optional<Author> after = authorRepository.findById(id);
        assertThat(after).isEmpty();
    }

    @Test
    void findByName() {
        String name = "Pushkin";
        Author author = new Author();
        author.setName(name);
        authorRepository.save(author);

        Optional<Author> optionalAuthor = authorRepository.findByName(name);

        assertThat(optionalAuthor).isPresent();
    }
}