package ro.mycode.librarymanagement.service;

import org.springframework.stereotype.Service;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.mappers.BookMapper;
import ro.mycode.librarymanagement.model.Book;
import ro.mycode.librarymanagement.repository.BookRepository;

import java.util.List;

@Service
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookQueryServiceImpl(BookRepository bookRepository, BookMapper bookMapper){
        this.bookRepository=bookRepository;
        this.bookMapper=bookMapper;
    }



    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByPrice(int min, int max) {
        return bookRepository.findByPrice(min,max);
    }

    @Override
    public boolean existsBookByTitleIgnoreCase(String title) {
        return bookRepository.existsBookByTitleIgnoreCase(title);
    }

    @Override
    public List<Book> findByTitleContaining(String partCuvant) {
        return bookRepository.findByTitleContaining(partCuvant);
    }

    @Override
    public Long countExpensive(int pret) {
        return bookRepository.countExpensive(pret);
    }

    @Override
    public List<Book> findByAuthorSorted(String author) {
        return bookRepository.findByAuthorSorted(author);
    }
}
