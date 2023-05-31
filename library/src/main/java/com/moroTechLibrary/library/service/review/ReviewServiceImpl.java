package com.moroTechLibrary.library.service.review;

import com.moroTechLibrary.library.dao.book.BookDAO;
import com.moroTechLibrary.library.dao.book.BookRepository;
import com.moroTechLibrary.library.dao.review.ReviewDAO;
import com.moroTechLibrary.library.dao.review.ReviewRepository;
import com.moroTechLibrary.library.dto.BookDTO;
import com.moroTechLibrary.library.dto.ReviewDTO;
import com.moroTechLibrary.library.exceptions.NotFoundException;
import com.moroTechLibrary.library.model.Book;
import com.moroTechLibrary.library.model.Review;
import com.moroTechLibrary.library.model.mapper.BookMapper;
import com.moroTechLibrary.library.model.mapper.ReviewMapper;
import com.moroTechLibrary.library.model.response.BookResponse;
import com.moroTechLibrary.library.model.response.ReviewResponse;
import com.moroTechLibrary.library.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final BookService bookService;
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ReviewMapper reviewMapper;
    private final BookMapper bookMapper;


    @Override
    @Transactional
    public ResponseEntity<ReviewResponse> createReview(Long bookId, Review review) {
        Objects.requireNonNull(bookId, "bookId must not be null");
        Objects.requireNonNull(review, "reviewDTO must not be null");

        ResponseEntity<BookResponse> bookResponseEntity = bookService.getBookDetails(bookId);
        BookResponse bookResponse = bookResponseEntity.getBody();

        if (bookResponse == null) {
            throw new NotFoundException("Book not found with ID: " + bookId);
        }


        List<ReviewDAO> reviewDAO = reviewMapper.convertToListDAO(bookResponseEntity.getBody().getBooks().get(0).getReviews());
        reviewDAO.get(0).setBookDAOList((List<BookDAO>) bookMapper.convertToDAO(bookResponseEntity.getBody().getBooks().get(0)));
        reviewRepository.save(reviewDAO);

        Double averageRating = reviewRepository.getAverageRatingByBook(bookId);
        book.setAverageRating(averageRating);
        bookRepository.save(book);

        ReviewDTO createdReviewDTO = reviewMapper.convertToDto(reviewDAO);
        ReviewResponse reviewResponse = ReviewResponse.builder()
                .review(createdReviewDTO)
                .message("Review created successfully")
                .build();

        return ResponseEntity.ok(reviewResponse);
    }
}
