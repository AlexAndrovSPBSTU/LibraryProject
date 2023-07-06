package ru.alexandrov.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.springcourse.models.Reader;

@Repository
public interface ReadersRepository extends JpaRepository<Reader, Integer> {
    Reader findByName(String name);
}
