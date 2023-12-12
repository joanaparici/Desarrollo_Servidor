package com.Proyecto.poyect1.bussines.service.impl;

import java.util.List;
import java.util.Optional;

import com.Proyecto.poyect1.bussines.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.bussines.entity.Movie;
import com.Proyecto.poyect1.bussines.service.ActorsRepository;
import com.Proyecto.poyect1.bussines.service.DirectorRepository;
import com.Proyecto.poyect1.bussines.service.MoviesRepository;
import com.Proyecto.poyect1.bussines.service.MoviesService;
import com.Proyecto.poyect1.exceptions.ResourceNotFoundException;

@Service
public class MoviesServiceImpl implements MoviesService {
    @Autowired
    MoviesRepository moviesRepository;
    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    ActorsRepository actorsRepository;

    @Override
    public List<Movie> findMovies(Integer page, Integer LIMIT) {
        return moviesRepository.findMovies(page, LIMIT);
    }

    @Override
    public Movie findMoviesById(int id) {
        Movie movie = moviesRepository.findMoviesById(id).orElseThrow(()->new ResourceNotFoundException("pelicula no encontrada"));
        //movie.setDirector(directorRepository.findDirectorByMovieId(id));//Para mostrar los directores
    
        Director director = directorRepository.findDirectorByMovieId(id).orElse(null);
        movie.setDirector(director);
        movie.setActors(actorsRepository.findActorByMovieId(id));
        return movie;
    }

    @Override
    public int insert(Movie movie, int directorId) {
        Director director = directorRepository.findDirector(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));
        movie.setDirector(director);
        return moviesRepository.insert(movie);
    }

    @Override
    public void deleteMovie(int id) {

        moviesRepository.deleteMovie(id);
    }

    @Override
    public Integer getTotalRecords() {
        return moviesRepository.getTotalRecords();
    }

}
