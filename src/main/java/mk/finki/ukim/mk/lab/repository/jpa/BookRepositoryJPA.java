package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryJPA extends JpaRepository<Book, Long> {

    Book findByIsbn(String isbn);

    List<Book> findAllByBookStore(BookStore store);
//    Store findByBookStore
}
