package ru.aaxee.homework2.integration;

import com.google.common.collect.Sets;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.BookService;

import java.util.Set;

@Service
public class RosBookNadzor implements MessageHandler {

    private final BookService bookService;
    private Set<String> forbiddenWords;

    public RosBookNadzor(SubscribableChannel newBookChannel, BookService bookService) {
        this.bookService = bookService;
        newBookChannel.subscribe(this);
        forbiddenWords = Sets.newHashSet();
        forbiddenWords.add("telegram");
        forbiddenWords.add("аскорбиновая кислота");
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Book book = (Book) message.getPayload();
        if (forbiddenWords.stream().anyMatch(it ->
                book.getName().toLowerCase().contains(it)
        )) {
            try {
                bookService.delete(book.getId());
            } catch (LibraryException ignore) {
            }
        }
    }
}
