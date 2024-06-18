package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author findById(Long id);

    void save(String name, String surname, String biography, LocalDate birth);
}
