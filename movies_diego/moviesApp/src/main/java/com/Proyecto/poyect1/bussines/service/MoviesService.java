package com.Proyecto.poyect1.bussines.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.Proyecto.poyect1.bussines.entity.Movie;

public interface MoviesService {

    int insert(Movie movie, int directorId);
    List<Movie> findMovies(Integer page, Integer LIMIT);

    Integer getTotalRecords();

    Movie findMoviesById(int id);
    void deleteMovie(int id);
}
