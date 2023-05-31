package com.moroTechLibrary.library.model;


import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Review {
    private Long id;
    private Integer rating;
    private String reviewText;
    private List<Book> books;
}