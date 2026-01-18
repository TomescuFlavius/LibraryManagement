package ro.mycode.librarymanagement.service;

import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.dtos.BookUpdateRequest;

public interface BookCommandService {

    BookResponse create(BookCreateRequest request);
    BookResponse update(long bookId, BookUpdateRequest request);
    void delete(long bookId);
}
