package com.moroTechLibrary.library.controller.review;

import com.moroTechLibrary.library.dao.review.ReviewDAO;
import com.moroTechLibrary.library.dto.ReviewDTO;
import com.moroTechLibrary.library.model.Review;
import com.moroTechLibrary.library.model.response.ReviewResponse;
import com.moroTechLibrary.library.service.review.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ReviewControllerImplTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewControllerImpl reviewController;

    @InjectMocks
    private Review review;


    @Test
    void testCreateReview_ReturnsOkResponse() {
        // Mock data
        Long bookId = 1L;
        ReviewResponse mockReviewResponse = new ReviewResponse();

        ResponseEntity<ReviewResponse> responseEntity = ResponseEntity.ok(mockReviewResponse);
        when(reviewService.createReview(bookId, review)).thenReturn(responseEntity);

        ResponseEntity<ReviewResponse> response = reviewController.createReview(bookId, review);

        verify(reviewService, times(1)).createReview(bookId, review);

        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(mockReviewResponse, response.getBody());
    }

    @Test
    void testCreateReview_ReturnsErrorResponse() {
        Long bookId = 1L;

        when(reviewService.createReview(bookId, review)).thenThrow(new RuntimeException("Review error"));

        ResponseEntity<ReviewResponse> response = reviewController.createReview(bookId, review);

        verify(reviewService, times(1)).createReview(bookId, review);

        assertNotNull(response);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        assertNull(response.getBody());
    }
}
