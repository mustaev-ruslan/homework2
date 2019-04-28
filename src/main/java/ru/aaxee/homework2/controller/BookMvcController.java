package ru.aaxee.homework2.controller;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aaxee.homework2.domain.Book;
import ru.aaxee.homework2.dto.BookDto;
import ru.aaxee.homework2.exception.LibraryException;
import ru.aaxee.homework2.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookMvcController {

    private final BookService bookService;

    @GetMapping("/")
    public String findAll(Model model) {
        List<Book> books = bookService.find(null, null, null, null);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        List<Book> books = bookService.find(id, null, null, null);
        Book book = Iterables.getOnlyElement(books);
        model.addAttribute("book", book);
        return "show-book";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) throws LibraryException {
        bookService.delete(id);
        return findAll(model); // Так нормально делать?
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "add-book";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bookDto") BookDto bookDto, Model model) throws LibraryException {
        Book added = bookService.add(bookDto.getName(), bookDto.getAuthorsListString(), bookDto.getGenresListString());
        return show(added.getId(), model);
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        List<Book> books = bookService.find(id, null, null, null);
        Book book = Iterables.getOnlyElement(books);
        model.addAttribute("bookDto", new BookDto(book));
        return "update-book";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("bookDto") BookDto bookDto, Model model) throws LibraryException {
        bookService.update(bookDto.getId(), bookDto.getName(), bookDto.getAuthorsListString(), bookDto.getGenresListString());
        return show(bookDto.getId(), model);
    }

}
