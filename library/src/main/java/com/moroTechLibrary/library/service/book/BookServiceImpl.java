package com.moroTechLibrary.library.service.book;


import com.moroTechLibrary.library.dao.book.BookDAO;
import com.moroTechLibrary.library.dao.book.BookRepository;
import com.moroTechLibrary.library.dto.BookDTO;
import com.moroTechLibrary.library.exceptions.NotFoundException;
import com.moroTechLibrary.library.model.mapper.BookMapper;
import com.moroTechLibrary.library.model.Book;
import com.moroTechLibrary.library.model.response.BookResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final RestTemplate restTemplate;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    @Value("${gutendex.base-url}")
    private final String baseUrl;

    @Override
    public ResponseEntity<BookResponse> searchBooks(String searchTerm, int page, int pageSize) {
        String url = baseUrl + "/books?q=" + searchTerm + "&page=" + page + "&pageSize=" + pageSize;

        ResponseEntity<BookResponse> responseEntity = restTemplate.getForEntity(url, BookResponse.class);
        BookResponse searchResponse = responseEntity.getBody();

        if (responseEntity.getStatusCode().is2xxSuccessful() && searchResponse != null) {
            return responseEntity;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<BookResponse> getBookDetails(Long bookId) {
        Optional<BookDAO> bookDAOOptional = bookRepository.findById(bookId);

        if (bookDAOOptional.isPresent()) {
            BookDAO bookDAO = bookDAOOptional.get();
            Book book = bookMapper.convertDAOToBook(bookDAO);
            BookDTO bookDTO = bookMapper.convertToDto(book);
            List<BookDTO> bookDTOList = new ArrayList<>();
            bookDTOList.add(bookDTO);
            BookResponse bookResponse = new BookResponse(bookMapper.convertDAOtoBookModel(bookDTOList),"Books Details", 0,0);
            return ResponseEntity.ok(bookResponse);
        } else {
            throw new NotFoundException("Book not found with ID: " + bookId);
        }
    }
}
