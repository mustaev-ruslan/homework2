package ru.aaxee.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author add(String name) throws LibraryException {
        Optional<Author> existingAuthor = authorRepository.findByName(name);
        if (existingAuthor.isPresent()) {
            throw new LibraryException("Author with name " + name + " already exist");
        }
        Author author = new Author();
        author.setName(name);
        authorRepository.save(author);
        Optional<Author> addedAuthor = authorRepository.findByName(name);
        if (!addedAuthor.isPresent()) {
            throw new LibraryException("Fail to add " + name);
        }
        return addedAuthor.get();
    }

    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author update(String id, String name) throws LibraryException {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (!existingAuthor.isPresent()) {
            throw new LibraryException("Author with id " + id + " not exist");
        }
        authorRepository.save(new Author(id, name));
        Optional<Author> updatedAuthor = authorRepository.findById(id);
        if (!updatedAuthor.isPresent()) {
            throw new LibraryException("Fail to update " + existingAuthor);
        }
        return updatedAuthor.get();
    }

    public void delete(String id) throws LibraryException {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (!existingAuthor.isPresent()) {
            throw new LibraryException("Author with id " + id + " not exist");
        }
        authorRepository.deleteById(id);
    }

    Optional<Author> findByName(String authorName) {
        return authorRepository.findByName(authorName);
    }
}
