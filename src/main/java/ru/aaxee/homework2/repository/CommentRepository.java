package ru.aaxee.homework2.repository;

import ru.aaxee.homework2.domain.Comment;

import java.util.List;

public interface CommentRepository {

    void addComment(Comment comment);

    List<Comment> findAllByBookId(Long bookId);

}
