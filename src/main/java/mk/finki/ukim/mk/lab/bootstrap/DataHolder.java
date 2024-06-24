package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.impl.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepositoryJPA;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepositoryJPA;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepositoryJPA;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataHolder {
    public static List<Author> authorList = null;
    public static List<Book> bookList = null;
    public static List<BookStore> bookStores = null;
    private final AuthorRepositoryJPA authorRepositoryJPA;
    private final BookStoreRepositoryJPA bookStoreRepositoryJPA;
    private final BookRepositoryJPA bookRepositoryJPA;

    @PostConstruct
    public void init(){
        authorList = new ArrayList<>();
        bookList = new ArrayList<>();
        bookStores = new ArrayList<>();

        authorList.add(new Author("V", "Vlad", "Lab", LocalDate.now()));
        authorList.add(new Author("V", "Igor", "Goshev", LocalDate.now()));
        authorList.add(new Author("V", "Andrej", "Vlah", LocalDate.now()));
        authorList.add(new Author("V", "David", "Arsov", LocalDate.now()));
        authorList.add(new Author("V", "Stef", "Vasilev", LocalDate.now()));

        authorRepositoryJPA.saveAll(authorList);

        bookStores.add(new BookStore("BookStore1", "Skopje", "Adresa1", new ArrayList<>()));
        bookStores.add(new BookStore("BookStore2", "Tetovo", "Adresa2", new ArrayList<>()));
        bookStores.add(new BookStore("BookStore3", "Kumanovo", "Adresa3", new ArrayList<>()));
        bookStores.add(new BookStore("BookStore4", "Bitola", "Adresa4", new ArrayList<>()));
        bookStores.add(new BookStore("BookStore5", "Ohrid", "Adresa5", new ArrayList<>()));

        bookStoreRepositoryJPA.saveAll(bookStores);

        bookList.add(new Book("1", "Rich Dad Poor Dad", "Business", 2010, new ArrayList<>(), bookStores.get(0)));
        bookList.add(new Book("2", "Intro to Computer Science", "Science", 19995, new ArrayList<>(), bookStores.get(1)));
        bookList.add(new Book("3", "Book1", "Genre1", 2000, new ArrayList<>(), bookStores.get(2)));
        bookList.add(new Book("4", "Book2", "Genre2", 1900, new ArrayList<>(), bookStores.get(3)));
        bookList.add(new Book("5", "Book3", "Genre3", 2300, new ArrayList<>(), bookStores.get(4)));

        bookRepositoryJPA.saveAll(bookList);


    }


}
