package ru.aaxee.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.domain.Comment;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.repository.BookRepository;
import ru.aaxee.homework2.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    public Comment addComment(Long id, String text) throws LibraryException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Comment comment = new Comment(text);
            comment.setBook(optionalBook.get());
            return commentRepository.save(comment);
        } else {
            throw new LibraryException("Book with id " + id + " not exist");
        }
    }

    public List<Comment> findAllByBookId(Long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }
}
