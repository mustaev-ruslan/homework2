package ru.aaxee.homework2.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.aaxee.homework2.domain.Comment;

public interface CommentRepository extends ReactiveCrudRepository<Comment, Long> {

}
