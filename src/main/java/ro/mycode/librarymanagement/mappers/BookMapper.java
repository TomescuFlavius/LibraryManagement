package ro.mycode.librarymanagement.mappers;

import org.springframework.stereotype.Component;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.model.Book;

import java.util.List;

@Component
public class BookMapper {
    public Book toEntity(BookCreateRequest request) {
        return Book.builder()
                .title(request.title())
                .author(request.author())
                .price(request.price())
                .build();
    }

    public BookResponse toDto(Book book) {
        return BookResponse.builder()
                .id(Long.valueOf(book.getId()))
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
    }
    public List<BookResponse> toDtoList(List<Book> books) {
        return books.stream().map(this::toDto).toList();
    }
}