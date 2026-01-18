package ro.mycode.librarymanagement.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BookCreateRequest(
        @NotBlank(message = "Titlul este obligatoriu")
        @Size(min = 2, max = 100, message = "Titlul trebuie să aibă între 2 și 100 caractere")
        String title,

        @NotBlank(message = "Autorul este obligatoriu")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Numele autorului poate conține doar litere și spații")
        String author,

        int price
        ) {
}