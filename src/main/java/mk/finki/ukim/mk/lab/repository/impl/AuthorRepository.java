package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AuthorRepository{
    public List<Author> findAll(){
        return DataHolder.authorList.stream().collect(Collectors.toList());
    }

    public Optional<Author> findById(Long id){
        return DataHolder.authorList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
