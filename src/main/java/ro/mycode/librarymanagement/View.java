package ro.mycode.librarymanagement;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ro.mycode.librarymanagement.dtos.BookCreateRequest;
import ro.mycode.librarymanagement.mappers.BookMapper;
import ro.mycode.librarymanagement.model.Book;
import ro.mycode.librarymanagement.repository.BookRepository;

import java.util.Scanner;

@Component
public class View {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private Scanner scanner;

    public View(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.scanner = new Scanner(System.in);

        this.play();
    }

    private void play() {
        boolean running = true;
        while (running) {
            System.out.println("1. Adauga o carte");
            System.out.println("2. Vezi toate cartile");
            System.out.println("0. Iesire");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addBook();
                case "2" -> listBooks();
                case "0" -> running = false;
                default -> System.out.println("Invalida!");
            }
        }
    }

    @Transactional
    public void addBook() {
        try {
            System.out.print("Titlu: ");
            String title = scanner.nextLine();
            System.out.print("Autor: ");
            String author = scanner.nextLine();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Pagini: ");
            int pages = Integer.parseInt(scanner.nextLine());

            BookCreateRequest request = new BookCreateRequest(title, author, isbn);
            Book book = bookRepository.save(bookMapper.toEntity(request));
            System.out.println("Carte salvata: " + bookMapper.toDto(book));
        } catch (Exception e) {
            System.out.println("Eroare la salvare: " + e.getMessage());
        }
    }

    public void listBooks() {
        bookRepository.findAll().forEach(b -> System.out.println(bookMapper.toDto(b)));
    }
}