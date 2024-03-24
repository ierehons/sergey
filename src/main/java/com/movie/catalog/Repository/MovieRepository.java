package com.movie.catalog.Repository;

import java.util.List;
import com.movie.catalog.model.Tutorial;

public interface MovieRepository {
    int save(Tutorial book);
    int update(Tutorial book);
    int deleteById(Long id);
    List<Tutorial> findAll();
    List<Tutorial> findByGenre(String genre);
    List<Tutorial> findByTitleContaining(String title);
}
