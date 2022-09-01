package springMvcApp.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springMvcApp.models.Publisher;

import java.util.List;

@Component
public class PublisherDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PublisherDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Publisher> index(){
        return jdbcTemplate.query("SELECT * FROM Publisher",
                new BeanPropertyRowMapper<>(Publisher.class));
    }

    public Publisher show(int id){
        return jdbcTemplate.query("SELECT * FROM Publisher WHERE publisher_id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Publisher.class)).stream().findAny().orElse(null);
    }

    public void save(Publisher publisher) {
        jdbcTemplate.update("INSERT INTO publisher(publisher_title) VALUES (?)",
                publisher.getPublisher_title());
    }

    public void update(int id, Publisher publisher) {
        jdbcTemplate.update("UPDATE publisher SET publisher_title=? WHERE publisher_id=?",publisher.getPublisher_title(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM publisher WHERE publisher_id=?",id);
    }
}
