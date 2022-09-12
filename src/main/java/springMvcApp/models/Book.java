package springMvcApp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    @NotEmpty(message = "Should be not empty")
    private String title;
    @Column(name = "author")
    @NotEmpty(message = "Should be not empty")
    private String author;
    @Column(name = "year")
    @Min(value = 1500,message = "Must be more 1500")
    private int year;

    public Book() {
    }

    public Book(int book_id, String title, String author, int year) {
        this.id = book_id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int book_id) {
        this.id = book_id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
