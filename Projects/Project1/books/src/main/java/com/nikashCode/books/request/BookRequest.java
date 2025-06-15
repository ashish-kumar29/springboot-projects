package com.nikashCode.books.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class BookRequest {

    @Size(min = 1, max = 50, message = "Title should be between 1 and 50")
    private String title;

    @Size(min = 1, max = 30, message = "Title should be between 1 and 30")
    private String author;

    @Size(min = 1, max = 30, message = "Title should be between 1 and 30")
    private String category;

    @Min(value=1, message = "Rating should be greater than 0")
    @Max(value=5, message = "Rating should be lesser than 6")
    private int rating;

    public BookRequest(String title, String author, String category, int rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
