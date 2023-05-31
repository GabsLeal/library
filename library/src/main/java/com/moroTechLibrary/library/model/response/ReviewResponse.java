package com.moroTechLibrary.library.model.response;

import com.moroTechLibrary.library.model.Review;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ReviewResponse {
    private List<Review> review;
    private String message;
}