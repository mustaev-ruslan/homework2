package ru.aaxee.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aaxee.homework2.domain.Genre;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre add(String name) throws LibraryException {
        Optional<Genre> existingGenre = genreRepository.findByName(name);
        if (existingGenre.isPresent()) {
            throw new LibraryException("Genre with name " + name + " already exist");
        }
        genreRepository.addGenre(name);
        Optional<Genre> addedGenre = genreRepository.findByName(name);
        if (!addedGenre.isPresent()) {
            throw new LibraryException("Fail to add " + name);
        }
        return addedGenre.get();
    }

    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre update(Long id, String name) throws LibraryException {
        Optional<Genre> existingGenre = genreRepository.findById(id);
        if (!existingGenre.isPresent()) {
            throw new LibraryException("Genre with id " + id + " not exist");
        }
        genreRepository.updateGenre(id, name);
        Optional<Genre> updatedGenre = genreRepository.findById(id);
        if (!updatedGenre.isPresent()) {
            throw new LibraryException("Fail to update " + existingGenre);
        }
        return updatedGenre.get();
    }

    public void delete(Long id) throws LibraryException {
        Optional<Genre> existingGenre = genreRepository.findById(id);
        if (!existingGenre.isPresent()) {
            throw new LibraryException("Genre with id " + id + " not exist");
        }
        genreRepository.deleteById(id);
    }

    Optional<Genre> findByName(String genreName) {
        return genreRepository.findByName(genreName);
    }
}
