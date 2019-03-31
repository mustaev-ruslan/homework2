package ru.aaxee.homework2.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aaxee.homework2.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentJpaRepository implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    public List<Comment> findAllByBookId(Long bookId) {
        return em.createQuery("select c from Comment c where c.book.id = :bookId", Comment.class)
                .setParameter("bookId", bookId)
                .getResultList();
    }
}
