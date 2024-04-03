package com.movie.catalog.repository;

import com.movie.catalog.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcActor;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcActorRepository implements  actorRepository{
    @Autowired
    private JdbcActor jdbcActor;

    @Override
    public int save(Actor actor) {
        try {
            return jdbcActor.update("INSERT INTO actor (name, " +
                            "surName) VALUES(?,?)",
                    movie.getName(),
                    movie.getSurName());
        } catch (Exception ex) {
            return 1;
        }
    }
    @Override
    public int update(Actor actor) {
        try {
            return jdbcActor.update("UPDATE actor SET name=?, " +
                            "surName=?  WHERE id=?",
                    movie.getName(),
                    movie.getSurName());
        } catch(Exception ex) {
            return 1;
        }
    }
    @Override
    public Actor findById(Long id) {
        try {
            Actor actor = jdbcActor.queryForObject("SELECT * FROM actor WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Actor.class), id);

            return actor;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public int deleteById(Long id) {
        try {
            return jdbcActor.update("DELETE FROM actor WHERE id=?", id);
        } catch (Exception ex) {
            return 1;
        }
    }
}
