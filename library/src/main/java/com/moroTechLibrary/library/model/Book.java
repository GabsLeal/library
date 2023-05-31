package com.moroTechLibrary.library.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private Long id;
    private String title;
    private List<String> subjects;
    private List<Person> authors;
    private List<Person> translators;
    private List<String> bookshelves;
    private List<String> languages;
    private Boolean copyright;
    private String mediaType;
    private List<Format> formats;
    private List<Review> reviews;
    private int downloadCount;
}