package ru.aaxee.homework2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.domain.Comment;

import java.util.List;

import static com.google.common.collect.Iterables.getOnlyElement;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void addComment_findByBookId() {
        // given
        String text = "Хорошая книга, и комменатрии интересные";
        Book book = new Book();
        bookRepository.save(book);
        Comment comment = new Comment(text);
        comment.setBook(book);
        commentRepository.save(comment);
        String id = book.getId();

        // when
        List<Comment> found = commentRepository.findAllByBookId(id);

        // then
        assertThat(getOnlyElement(found).getText()).isEqualTo(text);
    }
}
