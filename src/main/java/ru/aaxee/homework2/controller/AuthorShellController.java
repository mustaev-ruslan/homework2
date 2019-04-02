package ru.aaxee.homework2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.aaxee.homework2.domain.Author;
import ru.aaxee.homework2.dto.Response;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.AuthorService;

@Log
@RequiredArgsConstructor
@ShellComponent
@ShellCommandGroup("CRUD Author")
public class AuthorShellController {

    private final AuthorService authorService;

    @ShellMethod("Add author")
    public Response addAuthor(String name) throws LibraryException {
        Author author = authorService.add(name);
        return Response.ok("Author " + author + " added");
    }

    @ShellMethod("Show authors by id")
    public Response findAuthor(String id) {
        return Response.of(authorService.findById(id));
    }

    @ShellMethod("Show all authors")
    public Response findAllAuthors() {
        return Response.of(authorService.findAll());
    }

    @ShellMethod("Update author")
    public Response updateAuthor(String id, String name) throws LibraryException {
        Author author = authorService.update(id, name);
        return Response.ok("Author updated: " + author);
    }

    @ShellMethod("Delete author by id")
    public Response deleteAuthor(String id) throws LibraryException {
        authorService.delete(id);
        return Response.ok("Author with id " + id + " deleted");
    }
}