package ro.mycode.librarymanagement;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.dtos.BookUpdateRequest;
import ro.mycode.librarymanagement.exceptions.BookAlreadyExistException;
import ro.mycode.librarymanagement.exceptions.BookNotFoundException;
import ro.mycode.librarymanagement.mappers.BookMapper;
import ro.mycode.librarymanagement.model.Book;
import ro.mycode.librarymanagement.repository.BookRepository;
import ro.mycode.librarymanagement.service.BookCommandService;
import ro.mycode.librarymanagement.service.BookQueryService;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;


@Component
public class View {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private BookQueryService bookQueryService;
    private BookCommandService bookCommandService;
    private BookCreateRequest bookCreateRequest;
    private Scanner scanner;

    public View(BookRepository bookRepository, BookMapper bookMapper, BookQueryService bookQueryService, BookCommandService bookCommandService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookQueryService=bookQueryService;
        this.bookCommandService=bookCommandService;


        this.update();

//        this.getAll();
//        this.getByAuthor("Ion Creanga");
//        this.getByTitle();
//        this.getBooksByPriceRange(0,1000);
//        this.existByTitle("Povesti");
//        this.getByTitleContaining("Pov");

//        this.updateAuthorByTitle();
//        this.maxPrice();
//        this.delete();
//        this.updatePriceByAuthor();
//        this.findLike();
//        this.countExpensive();
//        this.authorSorted();
//        this.delete();




    }

//    private void play() {
//        boolean running = true;
//        while (running) {
//            System.out.println("1. Adauga o carte");
//            System.out.println("2. Vezi toate cartile");
//            System.out.println("0. Iesire");
//
//            String choice = scanner.nextLine();
//            switch (choice) {
//                case "1" -> addBook();
//                case "2" -> listBooks();
//                case "0" -> running = false;
//                default -> System.out.println("Invalida!");
//            }
//        }
//    }


    public void getAll(){
        bookRepository.findAll().forEach(b -> System.out.println(bookMapper.toDto(b)));
    }

    public void getByAuthor(String author){
        List<Book> rez1=bookQueryService.findByAuthor(author);
        if (!rez1.isEmpty()){
            System.out.println(bookMapper.toDtoList(rez1));
        }
    }

    public void getByTitle(){
        List<Book> rez=bookQueryService.findByTitle("Amintiri din copilarie");
        if (!rez.isEmpty()) System.out.println(bookMapper.toDtoList(rez));
    }

    public void getBooksByPriceRange(int min, int max){
        List<Book> rez=bookQueryService.findByPrice(min,max);
        rez.forEach(System.out::println);
        if (!rez.isEmpty()){
            System.out.println("Lista carti:" + bookMapper.toDtoList(rez));
        }
    }

    public void existByTitle(String title){
        boolean exist=bookQueryService.existsBookByTitleIgnoreCase(title);
        System.out.println(exist);
    }

    public void getByTitleContaining(String partTitle){
        List<Book> rez=bookQueryService.findByTitleContaining("Pov");
        System.out.println(bookMapper.toDtoList(rez));
    }


    public void countExpensive() {
        System.out.println("Nr carti scumpe: " + bookQueryService.countExpensive(50));
    }

    public void authorSorted() {
        bookQueryService.findByAuthorSorted("Ion Creanga").forEach(b -> System.out.println(bookMapper.toDto(b)));
    }

















    public void addBook() {

            System.out.print("Titlu: ");
            String title = scanner.nextLine();
            System.out.print("Autor: ");
            String author = scanner.nextLine();
            System.out.print("Pret: ");
            int price = Integer.parseInt(scanner.nextLine());

            BookCreateRequest request = new BookCreateRequest(title, author, price);
        try {
            BookResponse book = bookCommandService.create(request);
            System.out.println("Created Book: " + book);
        } catch (BookAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }


    public void update() {
        System.out.println("Id:");
        assert scanner != null;
        long bookId = Long.parseLong(scanner.nextLine());
        System.out.println("Title");
        String title = scanner.nextLine();
        System.out.println("Autor");
        String author=scanner.nextLine();
        System.out.println("Pret:");
        int price = Integer.parseInt(scanner.nextLine());


        BookUpdateRequest request = new BookUpdateRequest(title, author, price);
        try {
            BookResponse response = bookCommandService.update(bookId, request);
            System.out.println("Utilizator actualizat: " + response);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(){
        System.out.println("ID carte de sters: ");
        long bookId = Long.parseLong(scanner.nextLine());
        try {
            bookCommandService.delete(bookId);
            System.out.println("Carte stearsa cu succes.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }





}