package com.moroTechLibrary.library.service.book;

import com.moroTechLibrary.library.dto.BookDTO;
import com.moroTechLibrary.library.model.Book;
import com.moroTechLibrary.library.model.response.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    ResponseEntity<BookResponse> searchBooks(String searchTerm, int page, int pageSize);
    ResponseEntity<BookResponse> getBookDetails(Long bookId);

}
