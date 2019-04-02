package ru.aaxee.homework2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.aaxee.homework2.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String bookId);

}
