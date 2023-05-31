package com.moroTechLibrary.library.service.review;

import com.moroTechLibrary.library.dto.ReviewDTO;
import com.moroTechLibrary.library.model.Review;
import com.moroTechLibrary.library.model.response.ReviewResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    ResponseEntity<ReviewResponse> createReview(Long bookId, Review review);
}
