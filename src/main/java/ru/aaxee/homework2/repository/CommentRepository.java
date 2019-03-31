package ru.aaxee.homework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aaxee.homework2.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBookId(Long bookId);

}
