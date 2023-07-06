package ru.alexandrov.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
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
//    @NotEmpty(message = "Birthday must not be empty")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

    // TODO: 06.07.2023 thymeleaf doesn't support timestamp, i guess
//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date created_at;

    // TODO: 06.07.2023 books shouldn't be uploaded eagerly, it has to be fixed in future
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> books;

    public Reader(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Reader() {
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
//                ", created_at=" + created_at +
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

    public Date getBirthday() {
        return birthday;
    }

//    public Date getCreated_at() {
//        return created_at;
//    }

//    public void setCreated_at(Date created_at) {
//        this.created_at = created_at;
//    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
