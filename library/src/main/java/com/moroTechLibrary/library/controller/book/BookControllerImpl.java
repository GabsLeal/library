package com.moroTechLibrary.library.controller.book;

import com.moroTechLibrary.library.model.Book;
import com.moroTechLibrary.library.model.response.BookResponse;
import com.moroTechLibrary.library.service.book.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Api(tags = "Book Controller")
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    @ApiOperation("Search books")
    @GetMapping("/search")
    public ResponseEntity<BookResponse> searchBooks(@RequestParam("searchTerm") String searchTerm,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        try {
            return bookService.searchBooks(searchTerm, page, pageSize);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @ApiOperation("Get book details")
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookDetails(@PathVariable("bookId") Long bookId) {
        try{
            return bookService.getBookDetails(bookId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
}