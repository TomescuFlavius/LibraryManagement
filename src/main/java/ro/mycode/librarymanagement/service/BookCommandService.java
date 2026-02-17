package ro.mycode.librarymanagement.service;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookPatchRequest;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.dtos.BookUpdateRequest;

public interface BookCommandService {

    BookResponse create(BookCreateRequest request);
    BookResponse patch(String title, BookPatchRequest request);
    void delete(String title);
    BookResponse update(String title, BookUpdateRequest request);
}
