package com.moroTechLibrary.library.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    private List<Book> books;
}