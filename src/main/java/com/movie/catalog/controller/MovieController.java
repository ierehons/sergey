package com.movie.catalog.controller;

import com.movie.catalog.model.Movie;
import com.movie.catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/api")
public class MovieController {

  @Autowired
  private MovieRepository movieRepository;

  @GetMapping("/movies")
  public ResponseEntity<List<Movie>> getAllMovies
          (@RequestParam(required = false) String name) {
    try {
      List<Movie> movies;

      if (name == null)
        movies = movieRepository.findAll();
      else
        movies = movieRepository.findByNameContaining(name);
      if (movies.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(movies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/movies/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
    Movie movie = movieRepository.findById(id);

    if (movie != null) {
      return new ResponseEntity<>(movie, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/movies")
  public ResponseEntity<String> createMovie(@RequestBody Movie movie) {
    try {
      movieRepository.save(movie);
      return new ResponseEntity<>("Movie was created successfully.",
              HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/movies/{id}")
  public ResponseEntity<String> updateMovie(@PathVariable("id") long id,
                                            @RequestBody Movie movie) {
    Movie _movie = movieRepository.findById(id);

    if (_movie != null) {
      _movie.setId(id);
      _movie.setName(movie.getName());
      _movie.setYear(movie.getYear());
      _movie.setShortDescription(movie.getShortDescription());
      _movie.setGenre(movie.getGenre());
      _movie.setRating(movie.getRating());
      movieRepository.update(_movie);
      return new ResponseEntity<>("Movie was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find Movie with id=" + id,
              HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/movies/{id}")
  public ResponseEntity<String> deleteMovie(@PathVariable("id") long id) {
    try {
      int result = movieRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find Movie with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("Movie was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete movie.",
              HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/movies")
  public ResponseEntity<String> deleteAllMovies() {
    try {
      int numRows = movieRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows +
              " Movie(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete movies.",
              HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}