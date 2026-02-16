package ro.mycode.librarymanagement.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String title) {
        super("Book not found with id: " + title);
    }
}

