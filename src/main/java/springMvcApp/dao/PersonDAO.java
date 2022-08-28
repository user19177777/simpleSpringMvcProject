package springMvcApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springMvcApp.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //READ CRUD
    public List<Person> index()  {
        //BeanPropertyRowMapper переводит строки из бд в объект через сеттеры
        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));
    }
    //READ CRUD
    public Person show (int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    //CREATE CRUD
    public void save(Person person){
        //для передачи параметров в запрос используется var args,а не массив Object[]
        jdbcTemplate.update("INSERT INTO Person(fio,year_of_birth) VALUES (?,?)",
                person.getFio(),person.getDateOfBirth());
    }
    //UPDATE CRUD
    public void update(int id,Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET fio=?,year_of_birth=? WHERE id=?",
                updatedPerson.getFio(),updatedPerson.getDateOfBirth(),id);
    }
    //DELETE CRUD
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }
}
