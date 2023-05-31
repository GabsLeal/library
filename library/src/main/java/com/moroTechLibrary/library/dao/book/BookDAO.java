package com.moroTechLibrary.library.dao.book;
import com.moroTechLibrary.library.dao.format.FormatDAO;
import com.moroTechLibrary.library.dao.person.PersonDAO;
import com.moroTechLibrary.library.dao.review.ReviewDAO;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "subjects")
    @ElementCollection
    private List<String> subjectsList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    @NonNull
    private List<PersonDAO> authorsDAOList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_translators",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<PersonDAO> translatorsDAOList;

    @Column(name = "bookshelves")
    @ElementCollection
    @NonNull
    private List<String> bookshelvesList;

    @Column(name = "languages")
    @ElementCollection
    @NonNull
    private List<String> languagesList;

    @Column(name = "copyright")
    @NonNull
    private Boolean copyright;

    @Column(name = "media_type")
    @NonNull
    private String mediaType;

    @ElementCollection
    @CollectionTable(name = "book_formats", joinColumns = @JoinColumn(name = "book_id"))
    @MapKeyColumn(name = "format_type")
    @Column(name = "format_url")
    @NonNull
    private List<FormatDAO> formats;

    @OneToMany(mappedBy = "bookDAO", cascade = CascadeType.ALL, orphanRemoval = true)
    @NonNull
    private List<ReviewDAO> reviewsDAOList;

    @Column(name = "download_count")
    @NonNull
    private Integer downloadCount;


}
