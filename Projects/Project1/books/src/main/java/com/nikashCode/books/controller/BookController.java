package com.nikashCode.books.controller;

import com.nikashCode.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController(){
        initializeBooks();
    }

    private void initializeBooks(){
        books.addAll(List.of(
                new Book("Title One", "Author One", "Science"),
                new Book("Title Two", "Author Two", "Social Science"),
                new Book("Title Three", "Author Three", "Maths"),
                new Book("Title Four", "Author Four", "Science"),
                new Book("Title Five", "Author Five", "Social Science"),
                new Book("Title Six", "Author Six", "Maths"),
                new Book("Title Seven", "Author Seven", "Maths")
        ));
    }

    @GetMapping("/api")
    public String firstAPI(){
        return "Hello Ashish!";
    }
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category){

        if(category == null){
            return books;
        }

//        List<Book> filteredBooks = new ArrayList<>();
//        for(Book book:books){
//            if(book.getCateogary().equalsIgnoreCase(category)){
//                filteredBooks.add(book);
//            }
//        }
//        return filteredBooks;

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/api/{sentence}")
    public String experimentPathVariableAPI(@PathVariable String sentence){
        return sentence;
    }

    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title){
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }


    @PostMapping
    public String createBook(@RequestBody Book newBook){
        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
        if(isNewBook){
            books.add(newBook);
            return "new Book Added";
        }
        else{
            return "Book already present";
        }
    }

    @PutMapping("/{title}")
    public String updateBook(@PathVariable String title, @RequestBody Book updatedBook){
        for(int i=0; i<books.size(); i++){
            if(books.get(i).getTitle().equalsIgnoreCase(title)){
                books.set(i, updatedBook);
                return "Book updated successfully";
            }
        }
        return "Book not found with title: "+title;
    }

    @DeleteMapping("/{title}")
    public String deleteBook(@PathVariable String title){
        if(books.removeIf(book -> book.getTitle().equalsIgnoreCase(title))){
            return "Book deleted successfully";
        }
        return "Book with given title: "+title+" not found";
    }

}
