package ro.mycode.librarymanagement.service;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.dtos.BookResponseList;

public interface BookQueryService {
    BookResponseList findAllBooks();
    BookResponseList findByAuthor(String author);
    BookResponseList findByTitle(String title);
    BookResponseList findByPrice (int min, int max );
    boolean existsBookByTitleIgnoreCase (String title);
    BookResponseList findByTitleContaining(String partCuvant);
    Long countExpensive(int pret);
    BookResponseList findByAuthorSorted(String author);
    BookResponse findBookByTitle(String title);
    BookResponseList search(String title, String author, Integer price);
}
