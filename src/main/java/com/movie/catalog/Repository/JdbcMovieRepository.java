package com.movie.catalog.Repository;

import com.movie.catalog.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieRepository implements MovieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Tutorial tutorial) {
        try {
            return jdbcTemplate.update("INSERT INTO tutorials (title, description, published) VALUES(?,?,?)",
                    tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished());
        } catch (Exception ex) {
            return 1;
        }
    }

    @Override
    public int update(Tutorial tutorial) {
        try {
            return jdbcTemplate.update("UPDATE tutorials SET title=?, description=?, published=? WHERE id=?",
                    tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished(), tutorial.getId());
        } catch(Exception ex) {
            return 1;
        }
    }

    @Override
    public Tutorial findById(Long id) {
        try {
            Tutorial tutorial = jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Tutorial.class), id);

            return tutorial;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        try {
            return jdbcTemplate.update("DELETE FROM tutorials WHERE id=?", id);
        } catch (Exception ex) {
            return 1;
        }
    }

    @Override
    public List<Tutorial> findAll() {
        try {
            return jdbcTemplate.query("SELECT * from tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Tutorial> findByPublished(boolean published) {
        try {
            return jdbcTemplate.query("SELECT * from tutorials WHERE published=?",
                    BeanPropertyRowMapper.newInstance(Tutorial.class), published);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        String q = "SELECT * from tutorials WHERE title LIKE '%" + title + "%'";
        try {
            return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Tutorial.class));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update("DELETE from tutorials");
        }catch (Exception ex) {
            return 1;
        }
    }
}