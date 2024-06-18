package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.exception.StoreNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, Long bookId);
    Book findBookByIsbn(String isbn);
    Optional<Book> findBookById(Long Id);

    void deleteById(long id);

//    void save(Long id, String title,String isbn, String genre, int year, long storeId);

//    save(String title,String isbn, String genre, int year, long storeId)
    void save(String title, String isbn, String genre, int year, Long storeId) throws StoreNotFoundException;

    List<Book> findByStore(Long storeId);
}
