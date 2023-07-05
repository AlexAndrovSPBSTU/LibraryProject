package ru.alexandrov.springcourse.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alexandrov.springcourse.models.Book;
import ru.alexandrov.springcourse.models.Reader;

import java.util.List;

@Component
public class BooksDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BooksDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Transactional
    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(show(id));
    }

    @Transactional
    public void update(Book updatedBook, int id) {
        Session session = sessionFactory.getCurrentSession();
        Book oldBook = session.get(Book.class, id);
        oldBook.setAuthor(updatedBook.getAuthor());
        oldBook.setTitle(updatedBook.getTitle());
        oldBook.setYear(updatedBook.getYear());
    }

    @Transactional
    public Reader getBookOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id).getOwner();
    }

    @Transactional
    public void free(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.get(Book.class, id).setOwner(null);
    }

    @Transactional()
    public void assign(int bookId, int readerId) {
        Session session = sessionFactory.getCurrentSession();
        Reader reader = session.get(Reader.class, readerId);
        Book book = session.get(Book.class, bookId);
        book.setOwner(reader);
    }
}
