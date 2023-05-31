package com.moroTechLibrary.library.controller.review;

import com.moroTechLibrary.library.dto.ReviewDTO;
import com.moroTechLibrary.library.model.Review;
import com.moroTechLibrary.library.model.response.ReviewResponse;
import com.moroTechLibrary.library.service.review.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@AllArgsConstructor
@Api(tags = "Review Controller")
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;

    @Override
    @ApiOperation("Create a review")
    @PostMapping("/{bookId}/reviews")
    public ResponseEntity<ReviewResponse> createReview(Long bookId, Review review) {
        try{
            ResponseEntity<ReviewResponse> responseEntity = reviewService.createReview(bookId, review);
            if(responseEntity.getBody() != null){
                return responseEntity;
            } else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


