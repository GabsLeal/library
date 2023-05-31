package com.moroTechLibrary.library.dao.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<ReviewDAO, Long> {
    @Query("SELECT AVG(r.rating) FROM ReviewDAO r WHERE r.bookDAO.id = :bookId")
    Double getAverageRatingByBook(@Param("bookId") Long bookId);

}
