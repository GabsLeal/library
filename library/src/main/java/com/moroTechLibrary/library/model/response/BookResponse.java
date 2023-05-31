package com.moroTechLibrary.library.model.response;

import com.moroTechLibrary.library.model.Book;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class BookResponse {
    private List<Book> books;
    private String message;
    int page = 0;
    int pageSize = 10;

}
