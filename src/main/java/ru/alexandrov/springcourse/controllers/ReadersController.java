package ru.alexandrov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.springcourse.dao.ReadersDAO;
import ru.alexandrov.springcourse.models.Reader;
import ru.alexandrov.springcourse.util.ReaderValidator;

@Controller
@RequestMapping("/readers")
public class ReadersController {
    private final ReadersDAO readersDAO;
    private final ReaderValidator readerValidator;

    @Autowired
    public ReadersController(ReadersDAO readersDAO, ReaderValidator readerValidator) {
        this.readersDAO = readersDAO;
        this.readerValidator = readerValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("readers", readersDAO.index());
        return "readers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("reader", readersDAO.show(id));
        model.addAttribute("books",readersDAO.getReaderBooks(id));
        return "readers/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("reader") Reader reader) {
        return "readers/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("reader") @Valid Reader reader, BindingResult bindingResult) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "readers/new";
        }
        System.out.println(reader);
        readersDAO.save(reader);
        return "redirect:/readers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        readersDAO.delete(id);
        return "redirect:/readers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("reader", readersDAO.show(id));
        return "readers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("reader")@Valid Reader reader, @PathVariable("id") int id,BindingResult bindingResult) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "readers/edit";
        }
        readersDAO.update(reader, id);
        return "redirect:/readers";
    }
}
