package com.moroTechLibrary.library.controller.book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.moroTechLibrary.library.dto.BookDTO;
import com.moroTechLibrary.library.model.Book;
import com.moroTechLibrary.library.model.response.BookResponse;
import com.moroTechLibrary.library.service.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class BookControllerImplTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookControllerImpl bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchBooks_ReturnsOkResponse() {
        String searchTerm = "Harry Potter";
        int page = 0;
        int pageSize = 10;
        List<Book> mockBooks = Arrays.asList(
                Book.builder().id(1L).title("Harry Potter and the Philosopher's Stone").build(),
                Book.builder().id(2L).title("Harry Potter and the Chamber of Secrets").build()
        );

        ResponseEntity<BookResponse> responseEntity = ResponseEntity.ok((BookResponse) mockBooks);

        when(bookService.searchBooks(searchTerm, page, pageSize)).thenReturn(responseEntity);

        ResponseEntity<List<Book>> response = bookController.searchBooks(searchTerm, page, pageSize);

        verify(bookService, times(1)).searchBooks(searchTerm, page, pageSize);

        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(mockBooks, response.getBody());
    }

    @Test
    void testSearchBooks_ReturnsErrorResponse() {
        String searchTerm = "Invalid Search Term";
        int page = 0;
        int pageSize = 10;

        when(bookService.searchBooks(searchTerm, page, pageSize)).thenThrow(new RuntimeException("Search error"));

        ResponseEntity<BookResponse> response = bookController.searchBooks(searchTerm, page, pageSize);

        verify(bookService, times(1)).searchBooks(searchTerm, page, pageSize);

        assertNotNull(response);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetBookDetails_ReturnsOkResponse() {
        Long bookId = 1L;
        Book mockBook = Book.builder().id(bookId).title("Book Title").build();

        ResponseEntity<BookResponse> responseEntity = (ResponseEntity<BookResponse>) ResponseEntity.ok();

        when(bookService.getBookDetails(bookId)).thenReturn(responseEntity);

        ResponseEntity<Book> response = bookController.getBookDetails(bookId);

        verify(bookService, times(1)).getBookDetails(bookId);

        assertNotNull(response);

        assertEquals(mockBook, response.getBody());
    }

    @Test
    void testGetBookDetails_ReturnsErrorResponse() {
        Long bookId = 1L;

        when(bookService.getBookDetails(bookId)).thenThrow(new RuntimeException("Error retrieving book details"));

        ResponseEntity<Book> response = bookController.getBookDetails(bookId);

        verify(bookService, times(1)).getBookDetails(bookId);

        assertNotNull(response);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        assertNull(response.getBody());
    }
}
