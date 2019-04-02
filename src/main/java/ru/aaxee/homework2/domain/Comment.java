package ru.aaxee.homework2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Field
    @Column(name = "text")
    private String text;

    @Field
    @ManyToOne
    @JoinColumn
    private Book book;

    public Comment(String text) {
        this.text = text;
    }
}
