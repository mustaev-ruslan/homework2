package ru.aaxee.homework2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.dto.BookDto;
import ru.aaxee.homework2.service.BookService;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public Flux<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Book> show(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return bookService.delete(id);
    }

    @PostMapping
    public Mono<Book> add(@RequestBody BookDto bookDto) {
        return bookService.add(bookDto.getName(), bookDto.getAuthorsListString(), bookDto.getGenresListString());
    }

    @PutMapping
    public Mono<Book> update(@RequestBody BookDto bookDto) {
        return bookService.update(bookDto.getId(), bookDto.getName(), bookDto.getAuthorsListString(), bookDto.getGenresListString());
    }
}
