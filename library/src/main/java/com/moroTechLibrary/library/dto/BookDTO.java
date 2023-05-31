package com.moroTechLibrary.library.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private List<String> subjects;
    private List<PersonDTO> personDTOList;
    private List<String> bookshelves;
    private List<String> languages;
    private Boolean copyright;
    private String mediaType;
    private List<FormatDTO> formats;

    private List<ReviewDTO> reviewsDTO;
    private Integer downloadCount;
}
