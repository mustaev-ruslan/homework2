package ru.aaxee.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.repository.AuthorRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Mono<Author> add(String name) {
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }

    public Mono<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Flux<Author> findAll() {
        return authorRepository.findAll();
    }

    public Mono<Author> update(Long id, String name) {
        return authorRepository.save(new Author(id, name));
    }

    public Mono<Void> delete(Long id) {
        return authorRepository.deleteById(id);
    }
}
