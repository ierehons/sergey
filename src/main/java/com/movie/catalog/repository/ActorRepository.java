package com.movie.catalog.repository;

import com.movie.catalog.model.Actor;

import java.util.List;

public interface ActorRepository {
    int save(Actor book);

    int update(Actor book);

    int deleteById(Long id);

    List<Actor> findByName(String name);

    List<Actor> findBySurName(String surName);
}
