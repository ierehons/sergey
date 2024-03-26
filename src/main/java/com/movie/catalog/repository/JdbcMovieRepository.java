package com.movie.catalog.repository;

import com.movie.catalog.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class JdbcMovieRepository implements MovieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Movie movie) {
        try {
            return jdbcTemplate.update("INSERT INTO movies (name, " +
                            "year, shortDescription, genre, rating) VALUES(?,?,?,?,?)",
                    movie.getName(),
                    movie.getYear(),
                    movie.getShortDescription(),
                    movie.getGenre(),
                    movie.getRating());
        } catch (Exception ex) {
            return 1;
        }
    }

    @Override
    public int update(Movie movie) {
        try {
            return jdbcTemplate.update("UPDATE movies SET title=?, " +
                            "description=?, published=? WHERE id=?",
                    movie.getTitle(), movie.getDescription(), movie.isPublished(), movie.getId());
        } catch(Exception ex) {
            return 1;
        }
    }

    @Override
    public Movie findById(Long id) {
        try {
            Movie movie = jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Movie.class), id);

            return movie;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        try {
            return jdbcTemplate.update("DELETE FROM movies WHERE id=?", id);
        } catch (Exception ex) {
            return 1;
        }
    }

    @Override
    public List<Movie> findAll() {
        try {
            return jdbcTemplate.query("SELECT * from movies",
                    BeanPropertyRowMapper.newInstance(Movie.class));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Movie> findByPublished(boolean published) {
        try {
            return jdbcTemplate.query("SELECT * from movies WHERE published=?",
                    BeanPropertyRowMapper.newInstance(Movie.class), published);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Movie> findByTitleContaining(String title) {
        String q = "SELECT * from movies WHERE title LIKE '%" + title + "%'";
        try {
            return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Movie.class));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update("DELETE from movies");
        }catch (Exception ex) {
            return 1;
        }
    }
}