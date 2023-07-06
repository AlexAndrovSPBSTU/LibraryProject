package ru.alexandrov.springcourse.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alexandrov.springcourse.models.Book;
import ru.alexandrov.springcourse.models.Reader;

import java.util.List;
import java.util.Optional;

@Component
public class ReadersDAO {
//    @Autowired
//    private SessionFactory sessionFactory;


    @Transactional
    public List<Reader> index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select r from Reader r", Reader.class)
//                .getResultList();
        return null;
    }

    @Transactional
    public Reader show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Reader.class, id);
        return null;
    }


    @Transactional
    public Optional<Reader> show(String name) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select r from Reader r where name=" + name, Reader.class)
//                .getResultList().stream().findAny();

        return null;
    }

    @Transactional
    public void save(Reader reader) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(reader);
    }

    @Transactional
    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Reader.class, id));
    }

    @Transactional
    public void update(Reader updatedReader, int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Reader oldReader = session.get(Reader.class, id);
//        oldReader.setBirthday(updatedReader.getBirthday());
//        oldReader.setName(updatedReader.getName());
//        oldReader.setBirthday(updatedReader.getBirthday());
    }

    @Transactional
    public List<Book> getReaderBooks(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Reader r = session.get(Reader.class, id);
//        Hibernate.initialize(r.getBooks());
//        return r.getBooks();
        return null;
    }

}
