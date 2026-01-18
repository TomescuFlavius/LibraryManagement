package ro.mycode.librarymanagement.service;

import org.springframework.data.repository.query.Param;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.model.Book;

import java.util.List;

public interface BookQueryService {


    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findByPrice (@Param("minim") int min, @Param("maxim") int max );
    boolean existsBookByTitleIgnoreCase (@Param("title") String title);
    List<Book> findByTitleContaining(@Param("cuvant") String partCuvant);
    Long countExpensive(@Param("pret") int pret);
    List<Book> findByAuthorSorted(@Param("author") String author);


}
