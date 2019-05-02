package ru.aaxee.homework2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Comment {

    @Id
    private Long id;

    private String text;

    private Book book;

    public Comment(String text) {
        this.text = text;
    }
}
