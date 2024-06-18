package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/bookDetails")
public class BookDetailsController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookDetailsController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    //    isbn
    @PostMapping
    public String bookDetails(@RequestParam("isbn") String isbn, Model model, @RequestParam("authorId") Long authorId){
        Author author = authorService.findById(authorId);
        Book book = bookService.findBookByIsbn(isbn);
        bookService.addAuthorToBook(authorId, book.getId());
        model.addAttribute("book", book);
        return "bookDetails";
    }
}
