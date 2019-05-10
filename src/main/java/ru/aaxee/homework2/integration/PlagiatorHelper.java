package ru.aaxee.homework2.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.service.BookService;

import java.util.Random;

import static com.google.common.collect.Sets.newHashSet;

@Service
public class PlagiatorHelper {

    private final BookService bookService;

    public PlagiatorHelper(BookService bookService) {
        this.bookService = bookService;
    }

    @HystrixCommand(fallbackMethod = "writeBestseller", groupKey = "plagiator", commandKey = "plagiate")
    public void plagiateBook(Book original, String plagiatorName) {
        Random random = new Random();
        int n = random.nextInt(3);
        if (n != 0) {
            Book plagiate = new Book();
            plagiate.setName("Super " + original.getName());
            Author plagiator = new Author();
            plagiator.setName(plagiatorName);
            plagiate.setAuthors(newHashSet(plagiator));
            plagiate.setGenres(newHashSet(original.getGenres()));
            bookService.add(plagiate);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void writeBestseller(Book ignore, String plagiatorName) {
        Book book = new Book();
        book.setName("Bestseller");
        Author plagiator = new Author();
        plagiator.setName(plagiatorName);
        book.setAuthors(newHashSet(plagiator));
        bookService.add(book);
    }
}
