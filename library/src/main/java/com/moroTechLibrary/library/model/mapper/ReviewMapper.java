package com.moroTechLibrary.library.model.mapper;

import com.moroTechLibrary.library.dao.review.ReviewDAO;
import com.moroTechLibrary.library.dto.ReviewDTO;
import com.moroTechLibrary.library.model.Review;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Component
@Builder
public class ReviewMapper {
    private final ModelMapper modelMapper;


    public ReviewDTO convertToDto(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }

    public ReviewDAO convertToDAO(Review review) {
        return modelMapper.map(review, ReviewDAO.class);
    }

    public List<ReviewDTO> convertToListDto(List<Review> reviewList) {
        return reviewList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<Review> convertToListEntity(@NonNull List<ReviewDAO> reviewDTOList) {
        return reviewDTOList.stream()
                .map(this::convertDAOToReview)
                .collect(Collectors.toList());
    }

    public Review convertDAOToReview(ReviewDAO reviewDAO) {
        return modelMapper.map(reviewDAO, Review.class);
    }

    public List<ReviewDTO> convertToListDtoFromDAO(List<ReviewDAO> reviewDAOList) {
        return reviewDAOList.stream()
                .map(this::convertToDtoFromDAO)
                .collect(Collectors.toList());
    }

    public ReviewDTO convertToDtoFromDAO(ReviewDAO reviewDAO) {
        return modelMapper.map(reviewDAO, ReviewDTO.class);
    }
    public List<ReviewDAO> convertToListDAO(List<Review> reviewList) {
        return reviewList.stream()
                .map(this::convertToDAO)
                .collect(Collectors.toList());
    }


}
