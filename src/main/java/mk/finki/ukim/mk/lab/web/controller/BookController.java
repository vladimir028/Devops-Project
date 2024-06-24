package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.model.exception.StoreNotFoundException;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;
    private final BookStoreService bookStoreService;
    public BookController(BookService bookService, ReviewService reviewService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = bookService.listBooks();
//        Map<Long, Double> mapByBookIdAndAvgScore = reviewService.groupedByBookId();
//        Map.Entry<Long, Double> maxAvg = null;
//        if(mapByBookIdAndAvgScore != null) {
//            maxAvg = mapByBookIdAndAvgScore.entrySet().stream()
//                    .max(Map.Entry.comparingByValue())
//                    .orElse(null);
//        }
//        else {
//            maxAvg.setValue(0.0);
//        }

//        Long bookId = maxAvg.getKey();
//        Book maxAvgScoreBook = bookService.findBookById(bookId).get();
//        String title = maxAvgScoreBook.getTitle();
//        model.addAttribute("reviews", reviews);
//        model.addAttribute("book", maxAvgScoreBook);
//        model.addAttribute("score", maxAvg.getValue());
//        model.addAttribute("maxAvgScoreBook", bookId);
        model.addAttribute("books", books);
        return "listBooks";
    }

//    /delete/701
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id){
        bookService.deleteById(id);
        return "redirect:/books";
    }

//    /edit-form/701
    @GetMapping("/edit-form/{id}")
    public String editBook(@PathVariable Long id, Model model){
        if(bookService.findBookById(id) != null){
            Optional<Book> book = bookService.findBookById(id);
            List<BookStore> stores = bookStoreService.findAll();

            model.addAttribute("book", book.get());
            model.addAttribute("stores", stores);
            return "add-book";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @GetMapping("/add-form")
    public String AddBook(Model model){
//            Book book = bookService.findBookById(id);
            List<BookStore> stores = bookStoreService.findAll();
//            model.addAttribute("book", book);
            model.addAttribute("stores", stores);
            return "add-book";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title, @RequestParam String isbn, @RequestParam String genre, @RequestParam int year, @RequestParam Long store
    , @RequestParam(required = false) Long id) throws StoreNotFoundException {
        long a = store;
//        save(String title,String isbn, String genre, int year, long storeId)
        this.bookService.save(isbn,title, genre, year, store);
        return "redirect:/books";
    }
}
