package ro.mycode.librarymanagement.service;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookPatchRequest;
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
    public BookResponse patch(String title, BookPatchRequest request) {
        Book book = bookRepository.findByTitle(title);
        if (!bookRepository.existsBookByTitleIgnoreCase(book.getTitle())){
            throw new BookNotFoundException(book.getTitle());
        }
        if (request.title().isPresent()) {
            book.setTitle(request.title().get());
        }
        if (request.author().isPresent()){
            book.setAuthor(request.author().get());
        }
        if (request.price().isPresent()){
            book.setPrice(request.price().get());
        }
        Book updatedBook= bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }
    @Override
    @Transactional
    public void delete(String title) {

        Book book= bookRepository.findByTitle(title);
        if (!bookRepository.existsBookByTitleIgnoreCase(book.getTitle())) throw new BookNotFoundException(title);
        bookRepository.delete(book);
    }
    @Override
    public BookResponse update(String title, BookUpdateRequest request) {
        Book book = bookRepository.findByTitle(title);
        if (!bookRepository.existsBookByTitleIgnoreCase(book.getTitle())){
            throw new BookNotFoundException(book.getTitle());
        }
        if (request.title()!=null&& !request.title().isBlank()) {
            book.setTitle(request.title());
        }
        if (request.author()!=null&&!request.author().isBlank()){
            book.setAuthor(request.author());
        }
        if (request.price()>0){
            book.setPrice(request.price());
        }
        Book updatedBook= bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }
}
