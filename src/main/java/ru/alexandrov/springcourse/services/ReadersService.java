package ru.alexandrov.springcourse.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.alexandrov.springcourse.models.Book;
import ru.alexandrov.springcourse.models.Reader;
import ru.alexandrov.springcourse.repositories.ReadersRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ReadersService {
    private final ReadersRepository readersRepository;

    @Autowired
    public ReadersService(ReadersRepository readersRepository) {
        this.readersRepository = readersRepository;
    }


    public List<Reader> index() {
        return readersRepository.findAll();
    }

    public Reader show(int id) {
        return readersRepository.findById(id).orElse(null);
    }

    //Spring data jpa doesn't see that we need owner's books. They're not uploaded, must though
    //It refuses to upload books, because it thinks that we don't use them.
    public List<Book> getReaderBooks(int id) {
        List<Book> list = Objects.requireNonNull(readersRepository.findById(id).orElse(null)).getBooks();
//        list.forEach(System.out::println);
        return list;
    }

    public void save(Reader reader) {
//        reader.setCreated_at(new Date());
        readersRepository.save(reader);
    }

    public void delete(int id) {
        readersRepository.deleteById(id);
    }

    public void update(Reader reader) {
        readersRepository.save(reader);
    }

    public Optional<Reader> findReaderByName(String name) {
        return Optional.ofNullable(readersRepository.findByName(name));
    }
}
