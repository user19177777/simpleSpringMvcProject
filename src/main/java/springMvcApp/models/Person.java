package springMvcApp.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Should be not empty")

    private String fio;
    @Min(value = 1900,message = "Should be greater than 1900")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.fio = name;
        this.yearOfBirth = age;
    }

    public int getDateOfBirth() {
        return yearOfBirth;
    }

    public void setDateOfBirth(int age) {
        this.yearOfBirth = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
