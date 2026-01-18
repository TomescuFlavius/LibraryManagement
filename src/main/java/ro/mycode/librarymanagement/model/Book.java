package ro.mycode.librarymanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "titlu", nullable = false)
    @NotBlank(message = "Titlul este obligatoriu")
    private String title;

    @NotBlank(message = "Autorul este obligatoriu")
    private String author;

    private int price;


}
