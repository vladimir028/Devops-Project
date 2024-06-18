package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.impl.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepositoryJPA;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorServiceClass implements AuthorService {

    private final AuthorRepositoryJPA authorRepository;

    public AuthorServiceClass(AuthorRepositoryJPA authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(String name, String surname, String biography, LocalDate birth) {
        Author author = new Author(name, surname, biography, birth);
        authorRepository.save(author);
    }
}
