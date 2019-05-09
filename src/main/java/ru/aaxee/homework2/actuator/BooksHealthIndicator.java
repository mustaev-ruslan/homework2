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
public class BooksHealthIndicator implements HealthIndicator {

    private final BookService bookService;

    @Override
    public Health health() {
        List<Book> books = bookService.findAll();
        if (books.isEmpty()) {
            return Health
                    .down()
                    .withDetail("Info", "Books not found")
                    .build();
        } else {
            return Health.up().withDetail("Books count ", books.size()).build();
        }
    }
}
