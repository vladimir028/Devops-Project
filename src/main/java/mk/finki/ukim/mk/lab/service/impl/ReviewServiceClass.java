package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepositoryJPA;
import mk.finki.ukim.mk.lab.repository.jpa.ReviewRepository;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewServiceClass implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepositoryJPA bookRepository;


    public ReviewServiceClass(ReviewRepository reviewRepository, BookRepositoryJPA bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Review> findAllBooks(Long Id) {
        return reviewRepository.findAllByBook_Id(Id);
    }

    @Override
    public List<Review> findAllReviewByTimestampBetween(LocalDateTime from, LocalDateTime to) {

        return reviewRepository.findAllReviewByTimestampBetween(from, to);
    }

    @Override
    public void save(String description, int score, Long id, LocalDateTime dateTime) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null){

        Review review = new Review(score, description, book, dateTime);
        reviewRepository.save(review);
        }
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Map<Long, Double> groupedByBookId() {
        List<Review> reviews = reviewRepository.findAll();
       return reviews.stream().collect(Collectors.groupingBy(
                i -> i.getBook().getId(),
                Collectors.averagingDouble(Review::getScore)
       ));
    }

//    public Map<Long, Double> groupedByBookId(){
//        List<Review> reviews = reviewRepository.findAll();
//       return reviews.stream().collect(Collectors.groupingBy(
//                i -> i.getBook().getId(),
//                Collectors.averagingDouble(Review::getScore)
//        ));

//    }
}
