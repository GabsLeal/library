package com.moroTechLibrary.library.dto;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Integer rating;
    private String reviewText;
    private List<BookDTO> books;
}