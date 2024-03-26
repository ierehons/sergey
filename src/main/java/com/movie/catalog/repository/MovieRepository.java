package com.movie.catalog.repository;

import java.util.List;
import com.movie.catalog.model.Movie;

public interface MovieRepository {
    int save(Movie book);
    int update(Movie book);
    int deleteById(Long id);
    List<Movie> findAll();
    List<Movie> findByGenre(String genre);
    List<Movie> findByTitleContaining(String title);
}
