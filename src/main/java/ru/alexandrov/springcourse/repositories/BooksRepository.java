package ru.alexandrov.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.springcourse.models.Book;


@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
