package ru.aaxee.homework2.service;

import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.repository.AuthorRepository;

import java.util.*;

public class FakeAuthorRepository implements AuthorRepository {

    private Map<Long, Author> internalMap = new HashMap<>();
    private Long counter = 1L;

    @Override
    public void addAuthor(String name) {
        internalMap.put(counter, new Author(counter, name));
        counter++;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(internalMap.get(id));
    }

    @Override
    public List<Author> findAll() {
        return new ArrayList<>(internalMap.values());
    }

    @Override
    public void updateAuthor(Long id, String name) {
        if (internalMap.containsKey(id)) {
            internalMap.put(id, new Author(id, name));
        } else {
            throw new RuntimeException("Cannot update non existing value in fake repository");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (internalMap.containsKey(id)) {
            internalMap.remove(id);
        } else {
            throw new RuntimeException("Cannot delete non existing value in fake repository");
        }
    }

    @Override
    public Optional<Author> findByName(String name) {
        return internalMap.values().stream().filter(author -> author.getName().equals(name)).findFirst();
    }
}
