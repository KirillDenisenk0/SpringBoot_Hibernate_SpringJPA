package spring_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name of book should be not empty!")
    @Size(min = 3, max = 900, message = "Length of book should be from 3 to 900 characters")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "Book must have author!")
    @Size(min = 1, max = 900, message = "Length of author`s name should be from 3 to 900 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Year must be greater than 1500!")
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;
    @Transient
    private boolean expired;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
