package ro.mycode.librarymanagement.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.mycode.librarymanagement.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(@Param("author") String author);


    List<Book> findByTitle(@Param("title") String title);


    @Query("""
            select b from Book b
            where b.price between :minim and :maxim
            order by b.price desc 
           """)
    List<Book> findByPrice (@Param("minim") int min, @Param("maxim") int max );


    boolean existsBookByTitleIgnoreCase (@Param("title") String title);

    @Query("select b from Book b where b.title like %:cuvant%")
    List<Book> findByTitleContaining(@Param("cuvant") String partCuvant);


    @Query("select max (b.price) from Book b where b.price is not null ")
    Double maxPrice();


    @Query("select count(b) from Book b where b.price > :pret")
    Long countExpensive(@Param("pret") int pret);

    @Query("select b from Book b where b.author = :author order by b.price asc")
    List<Book> findByAuthorSorted(@Param("author") String author);

}



