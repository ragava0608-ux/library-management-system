
package com.library.model;

public class Book {
    private int bookId;
    private String title;
    private Author author;
    private int totalCopies;
    private int availableCopies;

    public Book(int bookId, String title, Author author, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId
                + ", Title: " + title
                + ", Author: " + author.getName()
                + ", Available Copies: " + availableCopies
                + "/" + totalCopies;
    }
}
