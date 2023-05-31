package com.moroTechLibrary.library.dao.book;

import com.moroTechLibrary.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookDAO, Long> {

    @Query("SELECT AVG(r.rating) FROM ReviewDAO r WHERE r.bookDAO.id = :bookId")
    Double getAverageRatingByBook(@Param("bookId") Long bookId);

}
