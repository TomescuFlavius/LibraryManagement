package ro.mycode.librarymanagement.dtos;

import java.util.List;

public record BookResponseList(
        List<BookResponse> bookResponseList
) {
}
