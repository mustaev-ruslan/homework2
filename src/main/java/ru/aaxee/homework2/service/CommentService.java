package ru.aaxee.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import ru.aaxee.homework2.domain.Comment;
import ru.aaxee.homework2.repository.BookRepository;
import ru.aaxee.homework2.repository.CommentRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    public Mono<Comment> addComment(Long id, String text) {
        return bookRepository.findById(id)
                .flatMap(book -> {
                    Comment comment = new Comment(text);
                    comment.setBook(book);
                    Mono<Comment> save = commentRepository.save(comment);
                    return save;
                });
    }
}
