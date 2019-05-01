package ru.aaxee.homework2.controller;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.dto.BookDto;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.BookService;

import java.util.List;

@SuppressWarnings("UnnecessaryLocalVariable")
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        List<Book> books = bookService.find(null, null, null, null);
        return books;
    }

    @GetMapping("/{id}")
    public Book show(@PathVariable("id") Long id) {
        List<Book> books = bookService.find(id, null, null, null);
        Book book = Iterables.getOnlyElement(books);
        return book;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) throws LibraryException {
        bookService.delete(id);
        return "ok";
    }

    @PostMapping
    public Book add(@RequestBody BookDto bookDto) throws LibraryException {
        Book book = bookService.add(bookDto.getName(), bookDto.getAuthorsListString(), bookDto.getGenresListString());
        return book;
    }

    @PutMapping
    public Book update(@RequestBody BookDto bookDto) throws LibraryException {
        Book book = bookService.update(bookDto.getId(), bookDto.getName(), bookDto.getAuthorsListString(), bookDto.getGenresListString());
        return book;
    }
}
