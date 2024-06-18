package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Author> authorList = null;
    public static List<Book> bookList = null;
    public static List<BookStore> bookStores = null;

//    @PostConstruct
//    public void init(){
//        authorList = new ArrayList<>();
//        bookList = new ArrayList<>();
//        bookStores = new ArrayList<>();
//
//        authorList.add(new Author(1L, "Vlad", "Lab", "Biography"));
//        authorList.add(new Author(2L, "Igor", "Goshev", "Biography"));
//        authorList.add(new Author(3L, "Andrej", "Vlah", "Biography"));
//        authorList.add(new Author(4L, "David", "Arsov", "Biography"));
//        authorList.add(new Author(5L, "Stef", "Vasilev", "Biography"));
//
//        bookStores.add(new BookStore("BookStore1", "Skopje", "Adresa1", new ArrayList<>()));
//        bookStores.add(new BookStore("BookStore2", "Tetovo", "Adresa2", new ArrayList<>()));
//        bookStores.add(new BookStore("BookStore3", "Kumanovo", "Adresa3", new ArrayList<>()));
//        bookStores.add(new BookStore("BookStore4", "Bitola", "Adresa4", new ArrayList<>()));
//        bookStores.add(new BookStore("BookStore5", "Ohrid", "Adresa5", new ArrayList<>()));
//
//        bookList.add(new Book("1", "Rich Dad Poor Dad", "Business", 2010, new ArrayList<>(), bookStores.get(0)));
//        bookList.add(new Book("2", "Intro to Computer Science", "Science", 19995, new ArrayList<>(), bookStores.get(1)));
//        bookList.add(new Book("3", "Book1", "Genre1", 2000, new ArrayList<>(), bookStores.get(2)));
//        bookList.add(new Book("4", "Book2", "Genre2", 1900, new ArrayList<>(), bookStores.get(3)));
//        bookList.add(new Book("5", "Book3", "Genre3", 2300, new ArrayList<>(), bookStores.get(4)));
//
//
//    }


}
