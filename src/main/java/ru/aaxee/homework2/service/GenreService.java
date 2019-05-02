package ru.aaxee.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.aaxee.homework2.domain.Genre;
import ru.aaxee.homework2.repository.GenreRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class GenreService {

    private final GenreRepository genreRepository;

    public Mono<Genre> add(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genreRepository.save(genre);
    }

    public Mono<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    public Flux<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Mono<Genre> update(Long id, String name) {
        return genreRepository.save(new Genre(id, name));
    }

    public Mono<Void> delete(Long id) {
        return genreRepository.deleteById(id);
    }

}
