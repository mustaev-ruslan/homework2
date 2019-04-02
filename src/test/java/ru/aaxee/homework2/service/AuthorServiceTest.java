package ru.aaxee.homework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataMongoTest
@ComponentScan
@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    @BeforeEach
    void before() {
        authorRepository.deleteAll();
    }

    @Test
    void addAndFindByName() throws LibraryException {
        String name = "Esenin";
        authorService.add(name);
        Optional<Author> optionalAuthor = authorService.findByName(name);
        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get().getName()).isEqualTo(name);
    }

    @Test
    void findById() throws LibraryException {
        Author author = authorService.add("Author1");
        Optional<Author> optionalAuthor = authorService.findById(author.getId());
        assertThat(optionalAuthor).isPresent();
    }

    @Test
    void findAll() throws LibraryException {
        authorService.add("Author1");
        authorService.add("Author2");
        List<Author> authors = authorService.findAll();
        assertThat(authors).hasSize(2);
    }

    @Test
    void sameNamesThrowsException() throws LibraryException {
        String name = "Author1";
        authorService.add(name);
        assertThatThrownBy(() -> authorService.add(name));
    }

    @Test
    void update() throws LibraryException {
        String newName = "New name";
        Author author = authorService.add("Old name");
        String id = author.getId();
        authorService.update(id, newName);
        Optional<Author> optionalAuthor = authorService.findById(id);
        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get().getName()).isEqualTo(newName);
    }

    @Test
    void delete() throws LibraryException {
        Author author = authorService.add("Author1");
        String id = author.getId();
        authorService.delete(id);
        assertThat(authorService.findById(id)).isEmpty();
    }
}