package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookStore")
public class BookStoreController {

    private final BookStoreService service;
    private final BookService bookService;

    public BookStoreController(BookStoreService service, BookService bookService) {
        this.service = service;
        this.bookService = bookService;
    }

    @GetMapping
    public String bookStoreDetails(Model model){
        List<BookStore> stores = service.findAll();
        model.addAttribute("stores", stores);
        return "bookStoreDetails";
    }

    @GetMapping("/details/{id}")
    public String bookDetails(@PathVariable Long id, Model model){
        BookStore store = service.findAll().stream().filter(i -> i.getId().equals(id)).findFirst().get();
//        bookService.findByStore(id);
        List<Book> books = bookService.findByStore(id);
        model.addAttribute("books", books);
        model.addAttribute("store", store);
        return "bookDetailsPerStore";
    }
}
