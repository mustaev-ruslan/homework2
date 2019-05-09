package ru.aaxee.homework2.integration;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.BookService;

import java.util.Map;
import java.util.Set;

@Service
public class Министерство_правды implements MessageHandler {

    private final BookService bookService;
    private Map<String, String> changeWords;

    public Министерство_правды(SubscribableChannel newBookChannel, BookService bookService) {
        this.bookService = bookService;
        newBookChannel.subscribe(this);
        changeWords = ImmutableMap.of(
                "война", "мир",
                "свобода", "рабство",
                "незнание", "сила"
                );
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Book book = (Book) message.getPayload();
        if (changeWords.keySet().stream().anyMatch(it ->
                book.getName().contains(it)
        )) {
            try {
                Book changedBook = new Book();
                changedBook.setAuthors(Sets.newHashSet(book.getAuthors()));
                changedBook.setGenres(Sets.newHashSet(book.getGenres()));
                changedBook.setName(changeName(book.getName()));

                bookService.delete(book.getId());
                bookService.add(changedBook);
            } catch (LibraryException ignore) {
            }
        }
    }

    private String changeName(String name) {
        String newName = name;
        for (Map.Entry<String, String> entry : changeWords.entrySet()) {
            newName = newName.replaceAll(entry.getKey(), entry.getValue());
        }
        return newName;
    }
}
