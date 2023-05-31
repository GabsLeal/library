package com.moroTechLibrary.library.service.review;

import com.moroTechLibrary.library.dao.book.BookRepository;
import com.moroTechLibrary.library.dao.review.ReviewRepository;
import com.moroTechLibrary.library.model.mapper.ReviewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReviewServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview_ReturnsCreatedResponse() {

    }

    @Test
    void testCreateReview_ThrowsNotFoundException() {

        }
}