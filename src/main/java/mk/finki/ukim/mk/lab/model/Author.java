package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Author{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Convert(converter = AuthorFullnameConverter.class)
    AuthorFullname authorFullname;
    private String biography;
    private LocalDate dateOfBirth;
    @ManyToMany(mappedBy = "authors")
    private List<Book> bookList;


//    public Author(Long id, String name, String surname, String biography) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.biography = biography;
//    }

    public Author(String name, String surname, String biography, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }
    public Author() {

    }
}
