package springMvcApp.models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Should be not empty")
    private String name;
    @Column(name = "age")
    @Min(value = 10,message = "Should be greater than 10")
    private int age;

    public Person() {
    }

    public Person(int id, String fio, int yearOfBirth) {
        this.id = id;
        this.name = fio;
        this.age = yearOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String fio) {
        this.name = fio;
    }
}
