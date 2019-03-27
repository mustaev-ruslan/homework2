package ru.aaxee.homework2.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aaxee.homework2.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.Iterables.getOnlyElement;

@Repository
public class BookJpaRepository implements BookRepository {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Book book = new Book();
        book.setId(id);
        em.remove(em.contains(book) ? book : em.merge(book));
    }

    @Override
    public Optional<Book> findByName(String name) {
        TypedQuery<Book> query = em
                .createQuery("select b from Book b where b.name = :name", Book.class)
                .setParameter("name", name);
        return Optional.ofNullable(getOnlyElement(query.getResultList(), null));
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        em.persist(book);
    }

    @Override
    public List<Book> findByIds(Set<Long> idSet) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.id in :idSet", Book.class)
                .setParameter("idSet", idSet);
        return query.getResultList();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b " +
                        "join b.authors a " +
                        "where a = :author"
                , Book.class).setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenre(String genre) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b " +
                        "join b.genres g " +
                        "where g = :genre"
                , Book.class).setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        em.persist(book);
    }
}
