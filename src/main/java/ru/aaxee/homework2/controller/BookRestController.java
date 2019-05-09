package ru.aaxee.homework2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.dto.BookDto;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book show(@PathVariable("id") Long id) throws LibraryException {
        return bookService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) throws LibraryException {
        bookService.delete(id);
        return "{}";
    }

    @PostMapping
    public Book add(@RequestBody BookDto bookDto) throws LibraryException {
        return bookService.add(bookDto.getName(), bookDto.getAuthorsListString(), bookDto.getGenresListString());
    }

    @PutMapping
    public Book update(@RequestBody BookDto bookDto) throws LibraryException {
        return bookService.update(bookDto.getId(), bookDto.getName(), bookDto.getAuthorsListString(), bookDto.getGenresListString());
    }
}
