package com.movie.catalog.model;

public class Movie {
    private long id;
    private String name;
    private Integer year;
    private String shortDescription;
    private String genre;
    private Integer rating;

    public Movie(long id,
                 String name,
                 Integer year,
                 String shortDescription,
                 String genre,
                 Integer rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.shortDescription = shortDescription;
        this.genre = genre;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", shortDescription='" + shortDescription + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                '}';
    }
}
