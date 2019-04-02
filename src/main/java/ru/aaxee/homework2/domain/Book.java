package ru.aaxee.homework2.domain;

import lombok.Data;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.aaxee.homework2.annotation.CascadeSave;

import javax.persistence.*;
import java.util.Set;

@Document
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Field
    @Column(name = "name")
    private String name;

    @DBRef
    @CascadeSave
    private Set<Author> authors;

    @DBRef
    @CascadeSave
    private Set<Genre> genres;
}
