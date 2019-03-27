package ru.aaxee.homework2.repository;

import com.google.common.collect.Iterables;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aaxee.homework2.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Iterables.*;

@Log
@Repository
public class GenreJpaRepository implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        em.persist(genre);
        log.info(genre.getId().toString());
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    @Transactional
    public void updateGenre(Long id, String name) {
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        em.persist(genre);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Genre genre = new Genre();
        genre.setId(id);
        em.remove(em.contains(genre) ? genre : em.merge(genre));
    }

    @Override
    public Optional<Genre> findByName(String name) {
        TypedQuery<Genre> query = em
                .createQuery("select g from Genre g where g.name = :name", Genre.class)
                .setParameter("name", name);
        return Optional.ofNullable(getOnlyElement(query.getResultList(), null));
    }
}
