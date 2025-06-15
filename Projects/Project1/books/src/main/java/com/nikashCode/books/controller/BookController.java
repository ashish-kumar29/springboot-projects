package com.nikashCode.books.controller;

import com.nikashCode.books.entity.Book;
import com.nikashCode.books.exception.BookErrorResponse;
import com.nikashCode.books.exception.BookNotFoundException;
import com.nikashCode.books.request.BookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Tag(name="Book Rest API EndPoints", description = "Operation related to Books")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController(){
        initializeBooks();
    }


    private void initializeBooks(){
        books.addAll(List.of(
                new Book(1, "Cracking the coding Interview", "Author One", "DSA", 4),
                new Book(2, "grokking the system design interview", "Author TWO", "System Design", 5),
                new Book(3, "Introduction to Algorithms", "Author Three", "DSA", 2),
                new Book(4, "Java Concurrency in Practice", "Author Four", "Java", 3),
                new Book(5, "Head First Design Patterns", "Author Five", "LLD", 4),
                new Book(6, "Grokking the Object-Oriented Design Interview", "Author Six", "LLD", 5),
                new Book(7, "System Design Interview", "Author Seven", "System Design", 4)
        ));
    }

    @Operation(summary = "Get all Books", description = "Retrieve a list of all available books")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks(@Parameter(description = "Optional Query Parameter for Category")
                                   @RequestParam(required = false) String category){

        if(category == null){
            return books;
        }
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }


    @Operation(summary = "Get Book with given id", description = "Retieve a specific book with given Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById(@Parameter(description = "Id of Book to be retrieved")
                                @PathVariable @Min(value = 1) Long id){
        return books.stream()
                .filter(book -> book.getId()==id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book Not found - "+id)
                );
    }


    @Operation(summary = "Create new Book", description = "Add new book to the collection")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String createBook(@Valid @RequestBody BookRequest newBookRequest){
        Long id = books.isEmpty() ? 1 : books.get(books.size()-1).getId()+1;
        books.add(convertToBook(id, newBookRequest));
        return "Book with id: "+id+" got created";
    }

    @Operation(summary = "Update Book", description = "Update book with specific id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateBook(@Parameter(description = "Id of Book to be updated")
                               @PathVariable @Min(value = 1) Long id,
                           @Valid @RequestBody BookRequest updatedBookRequest){
        for(int i=0; i<books.size(); i++){
            if(books.get(i).getId()==id){
                books.set(i, convertToBook(id, updatedBookRequest));
                return;
            }
        }
        throw new BookNotFoundException("Book not found with id: " + id);
    }

    @Operation(summary = "Delete Book", description = "Delete specific book with Given Id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@Parameter(description = "Id of Book to be deleted")
                               @PathVariable @Min(value = 1) Long id){
        if(books.removeIf(book -> book.getId()==id)){
            return ;
        }
        throw new BookNotFoundException("Book not found with id: " + id);
    }


    private Book convertToBook(long id, BookRequest bookRequest){
        return new Book(id, bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getCategory(), bookRequest.getRating());
    }
}
