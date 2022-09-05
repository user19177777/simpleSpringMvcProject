package springMvcApp.models;

import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Should be not empty")
    @Size(min = 2,max = 15,message = "Should be between 2 and 15  characters")
    private String name;
    @Column(name = "age")
    @Min(value = 0,message = "Should be greater than 0")
    private int age;
    @Column(name = "email")
    @NotEmpty(message = "Should be not empty")
    @Email(message = "Should be valid")
    private String email;
    @Column(name = "address")
    //проверка регулярные выражения
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",message = "" +
            "Address should be in this format: Country, City, Postal code (6 digits)")
    private String address;

    public Person() {
    }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
