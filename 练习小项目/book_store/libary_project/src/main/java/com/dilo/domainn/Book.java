package com.dilo.domainn;

public class Book {
    private int bookId;
    private String name;
    private String author;
    private int price;
    private String type;
    private boolean isBorrowed;

    public Book() {
    }

    public Book(int bookId, String name, String author, int price, String type, boolean isBorrowed) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.price = price;
        this.type = type;
        this.isBorrowed = isBorrowed;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", isBorrowed=" + isBorrowed +
                '}';
    }
}
