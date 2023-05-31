package com.moroTechLibrary.library.response;

import com.moroTechLibrary.library.dto.BookDTO;
import com.moroTechLibrary.library.model.Book;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SearchResponse {
    private int count;
    private String next;
    private String previous;
    private List<BookDTO> bookDTOS;
}
