package ru.aaxee.homework2.integration;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.service.BookService;

import java.util.Objects;

import static com.google.common.collect.Sets.newHashSet;

@Service
public class Plagiator implements MessageHandler {

    private final BookService bookService;

    private final String plagiatorName;

    public Plagiator(BookService bookService, SubscribableChannel newBookChannel) {
        this.bookService = bookService;
        newBookChannel.subscribe(this);
        plagiatorName = "Иван Плагиатов";
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Book original = (Book) message.getPayload();
        if (original.getAuthors().stream().noneMatch(it ->
                Objects.equals(it.getName(), plagiatorName)
        )) {
            Book plagiate = new Book();
            plagiate.setName("Super " + original.getName());
            Author plagiator = new Author();
            plagiator.setName(plagiatorName);
            plagiate.setAuthors(newHashSet(plagiator));
            plagiate.setGenres(newHashSet(original.getGenres()));
            bookService.add(plagiate);
        }
    }
}
