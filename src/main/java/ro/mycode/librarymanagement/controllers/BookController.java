package ro.mycode.librarymanagement.controllers;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.librarymanagement.dtos.*;
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
    @GetMapping("/{title}")
    public ResponseEntity<BookResponse> getByTitle(@PathVariable String title){
        log.debug("Http get api/books/{}", title);
        return ResponseEntity.ok(bookQueryService.findBookByTitle(title));
    }
    @GetMapping("/search")
    public ResponseEntity<BookResponseList> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer price)
    {log.debug("Http GET /api/books/search title={} author={} price={}", title, author, price);
        return ResponseEntity.ok(bookQueryService.search(title,author, price));}
    @PostMapping("/add")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookCreateRequest book){
        log.info("Http post /api/v1/books title={} author={} price={}", book.title(), book.author(), book.price());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookCommandService.create(book));
    }
    @PutMapping("/patch/{title}")
    public ResponseEntity<BookResponse> patchBook(@PathVariable String title, @RequestBody BookPatchRequest patched){
        log.info("Http put /api/v1/books/patch/{} title={} author={} price={}" ,title, patched.title(),patched.author(),patched.price());
        return ResponseEntity.ok(bookCommandService.patch(title,patched));
    }
    @PutMapping("/update/{title}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String title, @RequestBody BookUpdateRequest updated){
        log.info("Http put /api/v1/books/update/{} title={} author={} price={}" ,title, updated.title(),updated.author(),updated.price());
        return ResponseEntity.ok(bookCommandService.update(title,updated));
    }
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable String title){
        log.info("Http delete /api/v1/books/delete/{}", title);
        bookCommandService.delete(title);
        return ResponseEntity.noContent().build();
    }
}
