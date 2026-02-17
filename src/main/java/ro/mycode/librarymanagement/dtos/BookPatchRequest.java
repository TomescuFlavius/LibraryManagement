package ro.mycode.librarymanagement.dtos;

import java.util.Optional;

public record BookPatchRequest (
        Optional<String> title,
        Optional<String> author,
        Optional<Integer> price){
}
