package ru.aaxee.homework2.integration;

import lombok.extern.java.Log;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import ru.aaxee.homework2.domain.Book;

@Log
@Service
public class LibraryMagazine implements MessageHandler {

    public LibraryMagazine(SubscribableChannel newBookChannel) {
        newBookChannel.subscribe(this);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Book book = (Book) message.getPayload();
        log.info("Added book: " + book);
    }
}
