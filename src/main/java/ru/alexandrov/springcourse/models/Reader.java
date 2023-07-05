package ru.alexandrov.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "Reader")
public class Reader {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name must not be empty")
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    @NotEmpty(message = "Birthday must not be empty")
    private String birthday;
    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)
    private List<Book> books;

    public Reader(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Reader() {
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", books=" + books +
                '}';
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
