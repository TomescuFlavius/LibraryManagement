package ro.mycode.librarymanagement.mappers;

import org.springframework.stereotype.Component;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.model.Book;

@Component
public class BookMapper {
    public Book toEntity(BookCreateRequest request) {
        return Book.builder()
                .title(request.title())
                .author(request.author())
                .isbn(request.isbn())
                .build();
    }

    public BookResponse toDto(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .build();
    }
}