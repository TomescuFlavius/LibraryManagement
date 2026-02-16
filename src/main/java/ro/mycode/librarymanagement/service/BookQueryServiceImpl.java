package ro.mycode.librarymanagement.service;

import org.springframework.stereotype.Service;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.dtos.BookResponseList;
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
    public BookResponseList findByAuthor(String author) {
        return new BookResponseList(bookMapper.toDtoList(bookRepository.findByAuthor(author))) ;
    }

    @Override
    public BookResponseList findByTitle(String title) {

        return new BookResponseList(bookMapper.toDtoList(bookRepository.findBooksByTitle(title))) ;
    }

    @Override
    public BookResponseList findByPrice(int min, int max) {
        List<Book> books=bookRepository.findByPrice(min,max);
        return new BookResponseList(bookMapper.toDtoList(books));
    }

    @Override
    public boolean existsBookByTitleIgnoreCase(String title) {
        return bookRepository.existsBookByTitleIgnoreCase(title);
    }

    @Override
    public BookResponseList findByTitleContaining(String partCuvant) {
        return new BookResponseList(bookMapper.toDtoList(bookRepository.findByTitleContaining(partCuvant)));
    }

    @Override
    public Long countExpensive(int pret) {
        return bookRepository.countExpensive(pret);
    }

    @Override
    public BookResponseList findByAuthorSorted(String author) {
        return new BookResponseList(bookMapper.toDtoList(bookRepository.findByAuthorSorted(author))) ;
    }

    @Override
    public BookResponse findBookByTitle(String title) {
        return bookMapper.toDto(bookRepository.findByTitle(title));
    }

    @Override
    public BookResponseList findAllBooks(){
        return new BookResponseList(bookMapper.toDtoList(bookRepository.findAll()));
    }
}
