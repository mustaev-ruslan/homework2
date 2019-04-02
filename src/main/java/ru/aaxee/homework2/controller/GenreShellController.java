package ru.aaxee.homework2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.aaxee.homework2.domain.Genre;
import ru.aaxee.homework2.dto.Response;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.GenreService;

@Log
@RequiredArgsConstructor
@ShellComponent
@ShellCommandGroup("CRUD Genre")
public class GenreShellController {

    private final GenreService genreService;

    @ShellMethod("Add genre")
    public Response addGenre(String name) throws LibraryException {
        Genre genre = genreService.add(name);
        return Response.ok("Genre " + genre + " added");
    }

    @ShellMethod("Show genres by id")
    public Response findGenre(String id) {
        return Response.of(genreService.findById(id));
    }

    @ShellMethod("Show all genres")
    public Response findAllGenres() {
        return Response.of(genreService.findAll());
    }

    @ShellMethod("Update genre")
    public Response updateGenre(String id, String name) throws LibraryException {
        Genre genre = genreService.update(id, name);
        return Response.ok("Genre updated: " + genre);
    }

    @ShellMethod("Delete genre by id")
    public Response deleteGenre(String id) throws LibraryException {
        genreService.delete(id);
        return Response.ok("Genre with id " + id + " deleted");
    }
}