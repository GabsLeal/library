package com.moroTechLibrary.library.dao.person;

import com.moroTechLibrary.library.dao.book.BookDAO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@Builder
@Getter
@Setter
public class PersonDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "birth_year")
    private Integer birthYear;

    @NonNull
    @Column(name = "death_year")
    private Integer deathYear;

    @NonNull
    @ManyToMany(mappedBy = "authors")
    private List<BookDAO> books;

    public PersonDAO(Long id, @NonNull String name, @NonNull Integer birthYear, @NonNull Integer deathYear, @NonNull List<BookDAO> books) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.books = books;
    }
}
