package ru.aaxee.homework2.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.service.BookService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlagiatorHealthIndicator implements HealthIndicator {

    private final BookService bookService;

    @Override
    public Health health() {
        List<Book> books = bookService.findAll();
        boolean plagiatorWorks = books.stream().anyMatch(it -> it.getName().startsWith("Super "));
        if (plagiatorWorks) {
            return Health
                    .up()
                    .withDetail("Plagiated books: ",
                            books.stream().filter(book ->
                                    book.getName().startsWith("Super ")
                            ).count()
                    )
                    .build();
        } else {
            return Health.down().build();
        }
    }
}
