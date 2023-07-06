package ru.alexandrov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.springcourse.models.Book;
import ru.alexandrov.springcourse.models.Reader;
import ru.alexandrov.springcourse.services.BooksService;
import ru.alexandrov.springcourse.services.ReadersService;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final ReadersService readersService;

    @Autowired
    public BooksController(BooksService booksDAO, ReadersService readersDAO) {
        this.booksService = booksDAO;
        this.readersService = readersDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", booksService.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("reader") Reader reader, Model model) {
        model.addAttribute("book", booksService.show(id));
        model.addAttribute("owner", booksService.getBookOwner(id));
        model.addAttribute("readers", readersService.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.show(id));
        return "books/edit";
    }

    @PatchMapping
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String free(@PathVariable("id") int id) {
        booksService.free(id);
        return "redirect:/books";
    }

    @PatchMapping("{id}/assign")
    public String assign(@PathVariable("id") int bookId, @ModelAttribute("reader") Reader reader) {
        booksService.assign(bookId, reader.getId());
        return "redirect:/books";
    }
}
