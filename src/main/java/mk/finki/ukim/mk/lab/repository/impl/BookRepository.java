package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    public List<Book> findAll(){
        return DataHolder.bookList.stream().collect(Collectors.toList());
    }

    public Book findByIsbn(String isbn){
        return DataHolder.bookList.stream().filter(i -> i.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public Book findById(long Id){
        return DataHolder.bookList.stream().filter(i -> i.getId() == Id).findFirst().orElse(null);
    }

    public void deleteById(long Id){
        DataHolder.bookList.removeIf(i -> i.getId() == Id);
    }


    public Author addAuthorToBook(Author author, Book book){
        Book foundBook = DataHolder.bookList.stream().filter(i -> i.getIsbn().toLowerCase().equals(book.getIsbn().toLowerCase())).findFirst().orElse(null);
        if(foundBook == null){
          throw new IllegalArgumentException();
        }
        List<Author> authorsL = DataHolder.authorList;
        List<Author> authors = foundBook.getAuthors();
        authors.add(author);
        List<Author> authorsL2 = DataHolder.authorList;
        return author;
    }

    public void save(Long id, String title, String isbn, String genre, int year, long storeId) {
//        DataHolder.bookList.removeIf(i -> i.getIsbn().equals(isbn));

        Optional<BookStore> store = DataHolder.bookStores.stream().filter(i -> i.getId() == storeId).findFirst();
        if(id == null){
            DataHolder.bookList.add(new Book(isbn, title, genre, year, new ArrayList<>(), store.get()));
            return;
        }
        Book book = DataHolder.bookList.stream().filter(i -> i.getId() == id).findFirst().get();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setGenre(genre);
        book.setYear(year);
        book.setBookStore(store.get());

    }

    public List<Book> findByStore(Long storeId) {
        List<Book> books = DataHolder.bookList.stream().filter(i -> i.getBookStore().getId().equals(storeId)).collect(Collectors.toList());
//        BookStore store = DataHolder.bookStores.stream().filter(i -> i.getId().equals(storeId)).findFirst().get();
//        if(store != null){
//            store.setBookList(books);
//        }
        return books;

    }
}
