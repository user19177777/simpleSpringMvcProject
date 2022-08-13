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
            //создает объект для sql запроса,sql injection
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
        Person person = null;
        try {
            //быстрее Statement(компилируется один раз),безопаснее
            PreparedStatement preparedStatement = connection.prepareStatement(
              "SELECT * FROM Person WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }
    //CREATE CRUD
    public void save(Person person){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INTO Person VALUES (?,?,?,?)"
            );
            preparedStatement.setInt(1,person.getId());
            preparedStatement.setString(2,person.getName());
            preparedStatement.setInt(3,person.getAge());
            preparedStatement.setString(4,person.getEmail());
            //меняет данные в базе(создает запись в базе)
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //UPDATE CRUD
    public void update(int id,Person updatedPerson){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
              "UPDATE Person SET name=?,age=?,email=?WHERE id=?"
            );
            preparedStatement.setString(1,updatedPerson.getName());
            preparedStatement.setInt(2,updatedPerson.getAge());
            preparedStatement.setString(3,updatedPerson.getEmail());
            preparedStatement.setInt(4,updatedPerson.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    //DELETE CRUD
    public void delete(int id){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
              "DELETE FROM Person WHERE id=?"
            );
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
