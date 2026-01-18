package ro.mycode.librarymanagement.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data


public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private int price;
}