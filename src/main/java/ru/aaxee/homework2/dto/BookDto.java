package ru.aaxee.homework2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.aaxee.homework2.domain.Book;

@Data
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String name;

    private String authorsListString;

    private String genresListString;

    public BookDto(Book book) {
        id = book.getId();
        name = book.getName();
//        authorsListString = book.getAuthors().stream().map(Author::getName).collect(Collectors.joining(","));
//        genresListString = book.getGenres().stream().map(Genre::getName).collect(Collectors.joining(","));
    }

}
