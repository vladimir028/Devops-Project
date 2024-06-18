package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.service.impl.AuthorServiceClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorServiceClass authorServiceClass;

    public AuthorController(AuthorServiceClass authorServiceClass) {
        this.authorServiceClass = authorServiceClass;
    }

    @PostMapping
    public String authors(@RequestParam(name = "bookIsbn") String isbn, Model model) {
        List<Author> authors = authorServiceClass.listAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("isbn", isbn);
        return "authorList";
    }

    @PostMapping("/add-author")
    public String addAuthor(@RequestParam String name, @RequestParam String surname, @RequestParam LocalDateTime birth, @RequestParam String biography){
        LocalDate date = LocalDate.from(birth);
        authorServiceClass.save(name, surname, biography, date);
        return "redirect:/books";
    }

    @GetMapping("/add-author")
    public String addAuthor(){
        return "add-author";
    }


}
