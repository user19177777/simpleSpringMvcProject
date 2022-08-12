package springMvcApp.dao;

import org.springframework.stereotype.Component;
import springMvcApp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;


    static {
        try {
            // проверяет с помощью подгрузился ли драйвер JDBC в оперативную память
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //создает  подключение к базе данных,тк static - один раз
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //READ CRUD
    public List<Person> index()  {
        List<Person>people = new ArrayList<>();
        try {
            //создает объект для sql запроса
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            //помещает результат запроса в resultSet,запрос не меняет данные в базе
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }
    //READ CRUD
    public Person show (int id){

        return null;
    }
    //CREATE CRUD
    public void save(Person person){
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() + "'," + person.getAge() + ",'" + person.getEmail() + "')";
            //меняет данные в базе(создает запись в базе)
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //UPDATE CRUD
    public void update(int id,Person updatedPerson){
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }
    //DELETE CRUD
    public void delete(int id){;
    }
}
