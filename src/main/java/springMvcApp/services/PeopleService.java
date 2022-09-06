package springMvcApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springMvcApp.models.Person;
import springMvcApp.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
//для всех методов будет @Transactional(readOnly = true)
@Transactional(readOnly = true)
public class PeopleService {

    private PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll (){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }
    //приоритет выше чем над классом  @Transactional
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id,Person updPerson){
        updPerson.setId(id);
        peopleRepository.save(updPerson);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public void findByName(String name){
        peopleRepository.findByName(name);
    }

}
