package ro.mycode.librarymanagement.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.dtos.BookResponseList;
import ro.mycode.librarymanagement.dtos.BookUpdateRequest;
import ro.mycode.librarymanagement.model.Book;
import ro.mycode.librarymanagement.service.BookCommandService;
import ro.mycode.librarymanagement.service.BookQueryService;

@RestController
@RequestMapping("/api/v1/books")
@Slf4j
public class BookController {

    private BookQueryService bookQueryService;
    private BookCommandService bookCommandService;

    public BookController(BookQueryService bookQueryService, BookCommandService bookCommandService){
        this.bookQueryService=bookQueryService;
        this.bookCommandService=bookCommandService;
    }


    @GetMapping("/all")
    public ResponseEntity<BookResponseList> getAllBooks(){
        log.info("Http  get  /api/v1/books/al");
        return ResponseEntity.ok(bookQueryService.findAllBooks());
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookCreateRequest book){
        log.info("Http post /api/v1/books title={} aauthor={} price={}", book.title(), book.author(), book.price());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookCommandService.create(book));
    }

    @PutMapping("/{title}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String title, @Valid @RequestBody BookUpdateRequest updated){
        log.info("Http put /api/books/{} title={} author={} price={}",title, updated.title(),updated.author(),updated.price());
        return ResponseEntity.ok(bookCommandService.update(title,updated));
    }
}
