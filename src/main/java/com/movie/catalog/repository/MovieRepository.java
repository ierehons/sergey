package com.movie.catalog.repository;

import com.movie.catalog.model.Movie;

import java.util.List;

public interface MovieRepository {
    int save(Movie book);

    int update(Movie book);

    Movie findById(Long id);

    int deleteById(Long id);

    List<Movie> findAll();

    List<Movie> findByGenre(Long genre);

    List<Movie> findByNameContaining(String name);

    List<Movie> deleteAll();


}
