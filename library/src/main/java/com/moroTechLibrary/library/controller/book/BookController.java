package com.moroTechLibrary.library.controller.book;


import com.moroTechLibrary.library.model.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@Api(tags = "Book Controller")
public interface BookController {

    @ApiOperation("Search books")
    @GetMapping("/search")
    ResponseEntity<List<Book>> searchBooks(@RequestParam("searchTerm") String searchTerm,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int pageSize);

    @ApiOperation("Get book details")
    @GetMapping("/{bookId}")
    ResponseEntity<Book> getBookDetails(@PathVariable("bookId") Long bookId);
}