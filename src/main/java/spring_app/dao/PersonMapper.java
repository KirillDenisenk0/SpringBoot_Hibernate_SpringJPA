package spring_app.dao;

import org.springframework.jdbc.core.RowMapper;
import spring_app.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    //логика по переводу строк в ResultSet в person

    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = null;
      /*  while (resultSet.next()) {
            person = new Person();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
        }*/
        return person;
    }
}
