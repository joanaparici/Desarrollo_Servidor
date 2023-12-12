package com.Proyecto.poyect1.bussines.service;

import java.util.List;
import java.util.Optional;

import com.Proyecto.poyect1.bussines.entity.Movie;


public interface MoviesRepository {

    int insert(Movie movie);
    List<Movie> findMovies(Integer page,Integer LIMIT);

    Integer getTotalRecords();

    Optional<Movie> findMoviesById(int id);

    void deleteMovie(int id);
}
