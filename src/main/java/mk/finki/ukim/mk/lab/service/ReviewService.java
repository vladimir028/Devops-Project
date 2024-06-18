package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ReviewService {
     List<Review> findAllBooks(Long Id);
     List<Review> findAllReviewByTimestampBetween(LocalDateTime from, LocalDateTime to);

     void save(String description, int score, Long id, LocalDateTime dateTime);

    List<Review> findAll();
    Map<Long, Double> groupedByBookId();
}
