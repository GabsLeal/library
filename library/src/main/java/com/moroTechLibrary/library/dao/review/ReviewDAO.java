package com.moroTechLibrary.library.dao.review;

import com.moroTechLibrary.library.dao.book.BookDAO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class ReviewDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Integer rating;

    @NonNull
    private String reviewText;

    @NonNull
    @OneToMany(mappedBy = "reviewDAO", cascade = CascadeType.ALL)
    private List<BookDAO> bookDAOList;

}
