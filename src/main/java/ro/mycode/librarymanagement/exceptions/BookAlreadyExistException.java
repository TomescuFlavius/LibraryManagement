package ro.mycode.librarymanagement.exceptions;

public class BookAlreadyExistException extends RuntimeException {
    public BookAlreadyExistException(String title) {
        super("Book already exist: " + title);
    }
}