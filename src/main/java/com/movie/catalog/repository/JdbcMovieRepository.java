package com.movie.catalog.repository;

import com.movie.catalog.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieRepository implements MovieRepository {

    @Autowired
    private JdbcTemplate jdbcMovie;

    @Override
    public int save(Movie movie) {
        try {
            return jdbcMovie.update("INSERT INTO movies (name, " +
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
            return jdbcMovie.update("UPDATE movies SET name=?, " +
                            "year=?, " +
                            "shortDescription=?, " +
                            "genre=?, " +
                            "rating=? " +
                            "WHERE id=?",
                    movie.getName(),
                    movie.getYear(),
                    movie.getShortDescription(),
                    movie.getGenre(),
                    movie.getRating(),
                    movie.getId());
        } catch (Exception ex) {

            return 1;
        }
    }

    @Override
    public Movie findById(Long id) {
        try {
            Movie movie = jdbcMovie.queryForObject("SELECT * FROM movies WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Movie.class), id);

            return movie;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        try {
            return jdbcMovie.update("DELETE FROM movies WHERE id=?", id);
        } catch (Exception ex) {
            return 1;
        }
    }

    @Override
    public List<Movie> findAll() {
        try {
            return jdbcMovie.query("SELECT * from movies",
                    BeanPropertyRowMapper.newInstance(Movie.class));
        } catch (Exception ex) {
            return null;
        }
    }


    @Override
    public List<Movie> findByGenre(Long genre) {
        try {
            return jdbcMovie.query("SELECT * from movies WHERE genre=?",
                    BeanPropertyRowMapper.newInstance(Movie.class), genre);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Movie> findByNameContaining(String name) {
        String q = "SELECT * from movies WHERE title LIKE '%" + name + "%'";
        try {
            return jdbcMovie.query(q, BeanPropertyRowMapper.newInstance(Movie.class));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcMovie.update("DELETE from movies");
        } catch (Exception ex) {
            return 1;
        }
    }
}