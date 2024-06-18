package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final BookService bookService;

    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public String getReviews(@PathVariable Long id, Model model){
        List<Review> reviews = reviewService.findAllBooks(id);
        Long bookId = id;
        double avgScore = reviews.stream().mapToDouble(i -> i.getScore()).average().orElse(0);
        model.addAttribute("avgScore", avgScore);
        model.addAttribute("reviews", reviews);
        model.addAttribute("bookId", bookId);
        return "reviews";
    }

    @PostMapping("/filteredDate")
    public String filteredReviews(@RequestParam Long id, @RequestParam LocalDateTime from, @RequestParam LocalDateTime to, Model model){
//        List<Review> reviews = reviewService.findAllBooks(id);
        List<Review> reviews = reviewService.findAllReviewByTimestampBetween(from, to);
        List<Review> finalReview = reviews.stream().filter(i -> i.getBook().getId().equals(id)).collect(Collectors.toList());
        model.addAttribute("reviews", finalReview);
        return "reviews";
    }

    @PostMapping("/add-review/{id}")
    public String addReview(@PathVariable Long id, @RequestParam String description, @RequestParam int score){
        String a = description;
        int s = score;
        LocalDateTime dateTime = LocalDateTime.now();
        reviewService.save(description, score, id, dateTime);
        return "redirect:/review/{id}";
    }

}
