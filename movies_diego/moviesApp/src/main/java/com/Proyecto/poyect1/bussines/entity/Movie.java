package com.Proyecto.poyect1.bussines.entity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Movie {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private Director director;
    private List<Actor> actors;
    
    public Movie(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

    public Movie(int id, String title, int year, int runtime, Director director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.director = director;
        
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", runtime=" + runtime ;
    }

}
