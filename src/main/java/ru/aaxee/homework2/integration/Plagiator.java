package ru.aaxee.homework2.integration;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.service.BookService;

import java.util.Objects;

@Service
public class Plagiator implements MessageHandler {

    private final String plagiatorName;

    private final PlagiatorHelper plagiatorHelper;

    public Plagiator(SubscribableChannel newBookChannel, PlagiatorHelper plagiatorHelper) {
        this.plagiatorHelper = plagiatorHelper;
        newBookChannel.subscribe(this);
        plagiatorName = "Иван Плагиатов";
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Book original = (Book) message.getPayload();
        if (original.getAuthors().stream().noneMatch(it ->
                Objects.equals(it.getName(), plagiatorName)
        )) {
            plagiatorHelper.plagiateBook(original, plagiatorName);
        }
    }
}
