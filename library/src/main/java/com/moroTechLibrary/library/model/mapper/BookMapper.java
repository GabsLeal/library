package com.moroTechLibrary.library.model.mapper;

import com.moroTechLibrary.library.dao.book.BookDAO;
import com.moroTechLibrary.library.dao.format.FormatDAO;
import com.moroTechLibrary.library.dao.person.PersonDAO;
import com.moroTechLibrary.library.dao.review.ReviewDAO;
import com.moroTechLibrary.library.dto.BookDTO;
import com.moroTechLibrary.library.dto.FormatDTO;
import com.moroTechLibrary.library.dto.PersonDTO;
import com.moroTechLibrary.library.dto.ReviewDTO;
import com.moroTechLibrary.library.model.Book;
import com.moroTechLibrary.library.model.Format;
import com.moroTechLibrary.library.model.Person;
import com.moroTechLibrary.library.model.Review;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Component
public class BookMapper {
    private final ModelMapper modelMapper;
    private final PersonMapper personMapper;
    private final FormatMapper formatMapper;
    private final ReviewMapper reviewMapper;


    public BookDTO convertToDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book convertToEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public BookDAO convertToDAO(Book book) {
        BookDAO bookDAO = modelMapper.map(book, BookDAO.class);
        List<PersonDAO> authorDAOs = personMapper.convertToListDAO(book.getAuthors());
        bookDAO.setAuthorsDAOList(authorDAOs);
        List<FormatDAO> formatDAOs = formatMapper.convertToListDAO(book.getFormats());
        bookDAO.setFormats(formatDAOs);
        List<ReviewDAO> reviewDAOs = reviewMapper.convertToListDAO(book.getReviews());
        bookDAO.setReviewsDAOList(reviewDAOs);
        return bookDAO;
    }

    public List<BookDTO> convertToListDto(List<Book> bookList) {
        return bookList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<Book> convertDAOtoBookModel(List<BookDTO> bookDAOList) {
        return bookDAOList.stream()
                .map(this::convertDAOToBook)
                .collect(Collectors.toList());
    }

    public List<Book> convertToListEntity(List<BookDTO> bookDTOList) {
        return bookDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public Book convertDAOToBook(BookDAO bookDAO) {
        Book book = modelMapper.map(bookDAO, Book.class);
        List<Person> authors = personMapper.convertToListEntity(bookDAO.getAuthorsDAOList());
        book.setAuthors(authors);
        List<Format> formats = formatMapper.convertDAOToListModel(bookDAO.getFormats());
        book.setFormats(formats);
        List<Review> reviews = reviewMapper.convertToListEntity(bookDAO.getReviewsDAOList());
        book.setReviews(reviews);
        return book;
    }

    public BookDAO convertToBookDAO(Book book) {
        BookDAO bookDAO = modelMapper.map(book, BookDAO.class);
        List<PersonDAO> authorDAOs = personMapper.convertToListDAO(book.getAuthors());
        bookDAO.setAuthorsDAOList(authorDAOs);
        List<FormatDAO> formatDAOs = formatMapper.convertToListDAO(book.getFormats());
        bookDAO.setFormats(formatDAOs);
        List<ReviewDAO> reviewDAOs = reviewMapper.convertToListDAO(book.getReviews());
        bookDAO.setReviewsDAOList(reviewDAOs);
        return bookDAO;
    }

    public List<BookDTO> convertToListDtoFromDAO(List<BookDAO> bookDAOList) {
        return bookDAOList.stream()
                .map(this::convertToDtoFromDAO)
                .collect(Collectors.toList());
    }

    public BookDTO convertToDtoFromDAO(BookDAO bookDAO) {
        BookDTO bookDTO = modelMapper.map(bookDAO, BookDTO.class);
        List<PersonDTO> authorsDTO = personMapper.convertDAOToListDto(bookDAO.getAuthorsDAOList());
        bookDTO.setPersonDTOList(authorsDTO);
        List<FormatDTO> formatsDTO = formatMapper.convertToListDtoFromDAO(bookDAO.getFormats());
        bookDTO.setFormats(formatsDTO);
        List<ReviewDTO> reviewsDTO = reviewMapper.convertToListDtoFromDAO(bookDAO.getReviewsDAOList());
        bookDTO.setReviewsDTO(reviewsDTO);
        return bookDTO;
    }
}
