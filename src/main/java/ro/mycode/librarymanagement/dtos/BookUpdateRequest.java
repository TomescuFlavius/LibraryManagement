package ro.mycode.librarymanagement.dtos;

public record BookUpdateRequest(
        String title,
        String author,
        int price
){};
