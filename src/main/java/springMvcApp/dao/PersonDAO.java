package springMvcApp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springMvcApp.models.Person;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PersonDAO {
    private final EntityManager entityManager;
    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void testNPlus1(){
        Session session = entityManager.unwrap(Session.class);
        //1 req
        //List<Person> people = session.createQuery("SELECT p from Person p",Person.class).getResultList();
        //N req
        //for (Person person:people) System.out.println(person.getName());

        //Solution
        //sql: A LEFT JOIN B
        //1 req
        List<Person> people = session.createQuery("" +
                "select p from Person p left join fetch p.itemps").getResultList();
        for (Person person:people) System.out.println(person.getName());
    }
}
