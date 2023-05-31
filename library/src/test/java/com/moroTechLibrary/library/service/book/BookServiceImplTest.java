package com.moroTechLibrary.library.service.book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.moroTechLibrary.library.dao.book.BookDAO;
import com.moroTechLibrary.library.dto.BookDTO;
import com.moroTechLibrary.library.exceptions.NotFoundException;
import com.moroTechLibrary.library.model.Book;
import com.moroTechLibrary.library.model.mapper.BookMapper;
import com.moroTechLibrary.library.model.response.BookResponse;
import com.moroTechLibrary.library.response.SearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private ResponseEntity<BookResponse> responseList;
    private ResponseEntity<BookResponse> responseSingle;

    private List<BookDAO> mockBookDAOs;
    private List<BookDTO> mockBookDTOs;

    @Mock
    private Book mockBook;

    @Mock
    private BookDAO mockBookDAO;

    @Mock
    private BookDTO mockBookDTO;

    @Mock
    private SearchResponse mockSearchResponse;

    @Mock
    private ResponseEntity<SearchResponse> mockResponseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchBooks_ReturnsOkResponse() {
        // Mock data
        String searchTerm = "Harry Potter";
        int page = 0;
        int pageSize = 10;

        when(restTemplate.getForEntity(anyString(), eq(SearchResponse.class))).thenReturn(mockResponseEntity);
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockResponseEntity.getBody()).thenReturn(mockSearchResponse);

        when(bookMapper.convertToEntityList(mockBookDTOs)).thenReturn(new ArrayList<>());

        responseList = bookService.searchBooks(searchTerm, page, pageSize);

        verify(restTemplate, times(1)).getForEntity(anyString(), eq(SearchResponse.class));

        verify(bookMapper, times(1)).convertToEntityList(mockBookDTOs);

        assertNotNull(responseList);

        assertEquals(HttpStatus.OK, responseList.getStatusCode());

        assertNotNull(responseList.getBody());
        assertTrue(responseList.hasBody());
    }

    @Test
    void testSearchBooks_ReturnsNotFoundResponse() {
        String searchTerm = "Invalid Search Term";
        int page = 0;
        int pageSize = 10;

        when(restTemplate.getForEntity(anyString(), eq(SearchResponse.class))).thenReturn(ResponseEntity.notFound().build());

        responseList = bookService.searchBooks(searchTerm, page, pageSize);

        verify(restTemplate, times(1)).getForEntity(anyString(), eq(SearchResponse.class));

        assertNotNull(responseList);

        assertEquals(HttpStatus.NOT_FOUND, responseList.getStatusCode());

        assertNull(responseList.getBody());
    }

    @Test
    void testGetBookDetails_ReturnsBook() {
        Long bookId = 1L;
        String url = "https://gutendex.com/books/" + bookId;

        when(restTemplate.getForEntity(url, SearchResponse.class)).thenReturn(mockResponseEntity);
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockResponseEntity.getBody()).thenReturn(mockSearchResponse);

        when(bookMapper.convertToEntity(mockBookDTO)).thenReturn(mockBook);

        responseSingle = bookService.getBookDetails(bookId);

        verify(restTemplate, times(1)).getForEntity(url, SearchResponse.class);

        verify(bookMapper, times(1)).convertToEntity(mockBookDTO);

        assertNotNull(responseSingle);

        assertEquals(HttpStatus.OK, responseSingle.getStatusCode());

        assertNotNull(responseSingle.getBody());
        assertEquals(mockBook, responseSingle.getBody());
    }

    @Test
    void testGetBookDetails_ThrowsNotFoundException() {
        Long bookId = 123L;
        String baseUrl = "https://gutendex.com";
        String url = baseUrl + "/books/" + bookId;

        ResponseEntity<SearchResponse> responseEntity = ResponseEntity.notFound().build();

        when(restTemplate.getForEntity(url, SearchResponse.class)).thenReturn(responseEntity);

        assertThrows(NotFoundException.class, () -> bookService.getBookDetails(bookId));

        verify(restTemplate).getForEntity(url, SearchResponse.class);
    }
}
