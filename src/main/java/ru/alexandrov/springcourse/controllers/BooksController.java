package ru.alexandrov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.springcourse.dao.BooksDAO;
import ru.alexandrov.springcourse.dao.ReadersDAO;
import ru.alexandrov.springcourse.models.Book;
import ru.alexandrov.springcourse.models.Reader;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;
    private final ReadersDAO readersDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, ReadersDAO readersDAO) {
        this.booksDAO = booksDAO;
        this.readersDAO = readersDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("reader") Reader reader, Model model) {
        model.addAttribute("book", booksDAO.show(id));
        model.addAttribute("owner", booksDAO.getBookOwner(id));
        model.addAttribute("readers", readersDAO.index());
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
        booksDAO.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksDAO.update(book, id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String free(@PathVariable("id") int id) {
        booksDAO.free(id);
        return "redirect:/books";
    }

    @PatchMapping("{id}/assign")
    public String assign(@PathVariable("id") int bookId, @ModelAttribute("reader") Reader reader) {
        booksDAO.assign(bookId, reader.getId());
        return "redirect:/books";
    }
}
