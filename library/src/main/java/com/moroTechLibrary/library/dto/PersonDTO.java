package com.moroTechLibrary.library.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    private List<BookDTO> books;
}
