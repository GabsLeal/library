package com.moroTechLibrary.library.dao.format;

import com.moroTechLibrary.library.dao.book.BookDAO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "format")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormatDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookDAO bookDAO;

}
