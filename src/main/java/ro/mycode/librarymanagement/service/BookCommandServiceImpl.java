package ro.mycode.librarymanagement.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.dtos.BookUpdateRequest;
import ro.mycode.librarymanagement.exceptions.BookAlreadyExistException;
import ro.mycode.librarymanagement.exceptions.BookNotFoundException;

import ro.mycode.librarymanagement.mappers.BookMapper;
import ro.mycode.librarymanagement.model.Book;
import ro.mycode.librarymanagement.repository.BookRepository;

@Service
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookCommandServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional
    public BookResponse create(BookCreateRequest request) {
        if (bookRepository.existsBookByTitleIgnoreCase(request.title())){
            throw new BookAlreadyExistException(request.title());
        }
        Book savedBook=bookRepository.save(bookMapper.toEntity(request));
        return bookMapper.toDto(savedBook);
    }

    @Override
    @Transactional
    public BookResponse update(long bookId, BookUpdateRequest request) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        if (request.title() != null && !request.title().isBlank()) {
            book.setTitle(request.title());
        }
        if (request.author()!= null && !request.author().isBlank()){
            book.setAuthor(request.author());
        }
        if (request.price()>0){
            book.setPrice(request.price());
        }
        Book updatedBook= bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    @Transactional
    public void delete(long bookId) {

        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));
        bookRepository.delete(book);
    }
}
