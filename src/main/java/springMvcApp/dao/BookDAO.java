package springMvcApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springMvcApp.models.Book;
import springMvcApp.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(title,author,year) VALUES (?,?,?)",
                book.getTitle(),book.getAuthor(),book.getYear());
    }

    public void update(int id,Book book){
        jdbcTemplate.update("UPDATE book SET title=?,author=?,year=? WHERE book_id=?",
                book.getTitle(),book.getAuthor(),book.getYear(),id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?",id);
    }

    //получаем человека, которому принадлежит книга с указанным id
    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id"+" WHERE Book.book_id = ?",
                new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE book_id=?",id);
    }

    public void assign(int id,Person selectedPerson){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?",
                selectedPerson.getId(),id);
    }
}
