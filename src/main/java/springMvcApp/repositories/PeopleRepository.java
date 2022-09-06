package springMvcApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springMvcApp.models.Person;
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
