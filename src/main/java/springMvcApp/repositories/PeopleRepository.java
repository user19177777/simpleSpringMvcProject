package springMvcApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springMvcApp.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    //пишем только сигнатуру метода по полям моделей
    List<Person> findByName(String name);
}
