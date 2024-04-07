package com.movie.catalog.repository;

import com.movie.catalog.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcActorRepository implements  ActorRepository{
    @Autowired
    private JdbcTemplate jdbcActor;

    @Override
    public int save(Actor actor) {
        try {
            return jdbcActor.update("INSERT INTO actor (name, " +
                            "surName) VALUES(?,?)",
                    actor.getName(),
                    actor.getSurName());
        } catch (Exception ex) {
            return 1;
        }
    }
    @Override
    public int update(Actor actor) {
        try {
            return jdbcActor.update("UPDATE actor SET name=?, " +
                            "surName=?  WHERE id=?",
                    actor.getName(),
                    actor.getSurName());
        } catch(Exception ex) {
            return 1;
        }
    }
    //    @Override
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

    @Override
    public List<Actor> findByName(String name) {
        return null;
    }

    @Override
    public List<Actor> findBySurName(String surName) {
        return null;
    }
}