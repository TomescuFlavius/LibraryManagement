package ro.mycode.librarymanagement;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.dtos.BookResponse;
import ro.mycode.librarymanagement.dtos.BookResponseList;
import ro.mycode.librarymanagement.dtos.BookUpdateRequest;
import ro.mycode.librarymanagement.exceptions.BookAlreadyExistException;
import ro.mycode.librarymanagement.exceptions.BookNotFoundException;
import ro.mycode.librarymanagement.mappers.BookMapper;
import ro.mycode.librarymanagement.repository.BookRepository;
import ro.mycode.librarymanagement.service.BookCommandService;
import ro.mycode.librarymanagement.service.BookQueryService;
import java.util.List;
import java.util.Scanner;




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
        this.scanner=new Scanner(System.in);


        this.getAll();
        this.delete();
        this.update();

        this.getByAuthor("Ion Creanga");
        this.getByTitle();
        this.getByTitleContaining("Amint");
        this.authorSorted();
        this.getBooksByPriceRange(1,10);
        this.existByTitle("Povesti");
        this.countExpensive();







    }

    private void play() {
        boolean running = true;
        while (running) {
            System.out.println("1. Adauga o carte");
            System.out.println("2. Vezi toate cartile");


            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addBook();
                case "2" -> getAll();
                case "0" -> running = false;
                default -> System.out.println("Invalida!");
            }
        }
    }


    public void getAll(){
        bookRepository.findAll().forEach(b -> System.out.println(bookMapper.toDto(b)));
    }

    public void getByAuthor(String author){
        List<BookResponse> rez1= bookQueryService.findByAuthor(author).bookResponseList();
        if (!rez1.isEmpty()){
            rez1.forEach(System.out::println);
        }
    }

    public void getByTitle(){
        List<BookResponse> rez= bookQueryService.findByTitle("Amintiri din copilarie").bookResponseList();
        if (!rez.isEmpty())
            rez.forEach(System.out::println);
    }

    public void getBooksByPriceRange(int min, int max){
        List<BookResponse> rez= bookQueryService.findByPrice(min,max).bookResponseList();

        if (!rez.isEmpty()){
            rez.forEach(System.out::println);
        }
    }

    public void existByTitle(String title){
        boolean exist=bookQueryService.existsBookByTitleIgnoreCase(title);
        System.out.println(exist);
    }

    public void getByTitleContaining(String partTitle){
        List<BookResponse> rez= bookQueryService.findByTitleContaining(partTitle).bookResponseList();
        rez.forEach(System.out::println);
    }


    public void countExpensive() {
        System.out.println("Nr carti scumpe: " + bookQueryService.countExpensive(50));
    }

    public void authorSorted() {
        bookQueryService.findByAuthorSorted("Ion Creanga").bookResponseList().forEach(System.out::println);
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
        System.out.println("Title");
        String bookTitle = scanner.nextLine();
        System.out.println("New Title");
        String title = scanner.nextLine();
        System.out.println("Autor");
        String author=scanner.nextLine();
        System.out.println("Pret:");
        int price = Integer.parseInt(scanner.nextLine());


        BookUpdateRequest request = new BookUpdateRequest(title, author, price);
        try {
            BookResponse response = bookCommandService.update(bookTitle, request);
            System.out.println("Utilizator actualizat: " + response);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(){
        System.out.println("Titlu carte de sters: ");
        String btitle = scanner.nextLine();
        try {
            bookCommandService.delete(btitle);
            System.out.println("Carte stearsa cu succes.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }





}