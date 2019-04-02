package ru.aaxee.homework2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.dto.Response;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.BookService;

@Log
@RequiredArgsConstructor
@ShellComponent
@ShellCommandGroup("CRUD Book")
public class BookShellController {

    private final BookService bookService;

    @ShellMethod("Add book")
    public Response addBook(
            @ShellOption String name,
            @ShellOption(defaultValue = ShellOption.NULL) String authors,
            @ShellOption(defaultValue = ShellOption.NULL) String genres
    ) throws LibraryException {
        Book book = bookService.add(name, authors, genres);
        return Response.ok("Book added: " + book);
    }

    @ShellMethod("Show books by query")
    public Response findBooks(
            @ShellOption(defaultValue = ShellOption.NULL) String id,
            @ShellOption(defaultValue = ShellOption.NULL) String name,
            @ShellOption(defaultValue = ShellOption.NULL) String author,
            @ShellOption(defaultValue = ShellOption.NULL) String genre
    ) {
        return Response.of(bookService.find(id, name, author, genre));
    }

    @ShellMethod("Update book")
    public Response updateBook(
            String id,
            @ShellOption(defaultValue = ShellOption.NULL) String name,
            @ShellOption(defaultValue = ShellOption.NULL) String authors,
            @ShellOption(defaultValue = ShellOption.NULL) String genres
    ) throws LibraryException {
        Book book = bookService.update(id, name, authors, genres);
        return Response.ok("Book updated: " + book);
    }

    @ShellMethod("Delete book by id")
    public Response deleteBook(String id) throws LibraryException {
        bookService.delete(id);
        return Response.ok("Book with id " + id + " deleted");
    }
}