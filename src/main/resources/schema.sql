-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE author (
  id BIGINT NOT NULL IDENTITY,
  name VARCHAR NOT NULL UNIQUE,
  PRIMARY KEY(id)
);

CREATE TABLE genre (
  id BIGINT NOT NULL IDENTITY,
  name VARCHAR NOT NULL UNIQUE,
  PRIMARY KEY(id)
);

CREATE TABLE book (
  id BIGINT NOT NULL IDENTITY,
  name VARCHAR NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE book_author (
  book_id BIGINT NOT NULL,
  author_id BIGINT NOT NULL,
  PRIMARY KEY (book_id, author_id),
  FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
  FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE book_genre (
  book_id BIGINT NOT NULL,
  genre_id BIGINT NOT NULL,
  PRIMARY KEY (book_id, genre_id),
  FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
  FOREIGN KEY (genre_id) REFERENCES genre(id)
);