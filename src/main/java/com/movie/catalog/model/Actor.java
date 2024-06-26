package com.movie.catalog.model;

public class Actor {
    private long id;
    private String name;
    private String surName;

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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Actor(long id, String name, String surName) {
        this.id = id;
        this.name = name;
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}
