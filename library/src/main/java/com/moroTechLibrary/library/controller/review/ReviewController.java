package com.moroTechLibrary.library.controller.review;

import com.moroTechLibrary.library.dto.ReviewDTO;
import com.moroTechLibrary.library.model.Review;
import com.moroTechLibrary.library.model.response.ReviewResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Api(tags = "Review Controller")
public interface ReviewController {

    @ApiOperation("Create a review")
    @PostMapping("/{bookId}/reviews")
    ResponseEntity<ReviewResponse> createReview(@PathVariable("bookId") Long bookId, @RequestBody Review review;
}
