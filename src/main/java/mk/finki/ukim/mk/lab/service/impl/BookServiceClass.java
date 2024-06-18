package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.exception.StoreNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepositoryJPA;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepositoryJPA;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepositoryJPA;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceClass implements BookService {

    private final BookRepositoryJPA bookRepository;
    private final BookStoreRepositoryJPA bookStoreRepository;
    private final AuthorRepositoryJPA authorRepository;

    public BookServiceClass(BookRepositoryJPA bookRepository, BookStoreRepositoryJPA bookStoreRepository, AuthorRepositoryJPA authorRepository) {
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId).get();
        Book book = bookRepository.findById(bookId).orElse(null);
        book.getAuthors().removeIf(i -> i.getId().equals(authorId));
        if(author != null && book != null) {
            book.getAuthors().add(author);
            bookRepository.save(book);
        }
        return author;
    }

//    @Override
//    public Author addAuthorToBook(Long authorId, String isbn) {
//        Author author = authorRepository.findById(authorId).get();
//        Book book = bookRepository.findByIsbn(isbn);
//        book.getAuthors().removeIf(i -> i.getId() == authorId);
//        if(author != null && book != null) {
//            book.getAuthors().add(author);
//            bookRepository.save(book);
//        }
//        return author;
//    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Optional<Book> findBookById(Long Id) {
        return bookRepository.findById(Id);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void save(String isbn,String title, String genre, int year, Long storeId) throws StoreNotFoundException {
        BookStore store = bookStoreRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException(storeId));
        Book book = bookRepository.findByIsbn(isbn);
        if(book == null)
            bookRepository.save(new Book(isbn, title, genre, year, new ArrayList<>(),store));
        else{
            book.setBookStore(store);
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setYear(year);
            book.setGenre(genre);
            bookRepository.save(book);
        }
    }

//    @Override
//    public Optional<Book> findByStore(Long storeId) {
//        BookStore store = bookStoreRepository.findAll().stream().filter(i -> i.getId() == storeId).findFirst().get();
//        return Optional.empty();
//    }

    @Override
    public List<Book> findByStore(Long storeId) {
        BookStore store = bookStoreRepository.findById(storeId).orElse(null);
         return bookRepository.findAllByBookStore(store);
    }
}
