package springMvcApp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springMvcApp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;
    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    //READ CRUD
    //автоматически открывает и закрывает транзакцию
    @Transactional(readOnly = true)
    public List<Person> index()  {
        Session session = sessionFactory.getCurrentSession();
        List<Person>people = session.createQuery("select i from Person i",Person.class).getResultList();
        return people;
    }
    //READ CRUD
    @Transactional(readOnly = true)
    public Person show(String email){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select i from Person i where Person.email=i.email",Person.class).getSingleResult();
    }
    @Transactional(readOnly = true)
    public Person show (int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }
    //CREATE CRUD
    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }
    //UPDATE CRUD
    @Transactional
    public void update(int id,Person updatedPerson){
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedPerson);
    }
    //DELETE CRUD
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Person.class,id));
    }

    public List<Person>create1000People(){
        long before = System.currentTimeMillis();
        List<Person>people = new ArrayList<>();
        for (int i=0;i<10;i++){
            people.add(new Person(i,"Name"+i,30,"test"+i+"@mail.ru", "address"));
        }
        long after = System.currentTimeMillis();
        System.out.println("Time to create 100: "+(after-before));
        return people;
    }
}
