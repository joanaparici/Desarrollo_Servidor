package com.Proyecto.poyect1.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Proyecto.poyect1.DBUtil.DBUtil;
import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.bussines.entity.Movie;
import com.Proyecto.poyect1.bussines.service.MoviesRepository;
import com.Proyecto.poyect1.mapper.MovieMapper;
import com.Proyecto.poyect1.persistence.DAO.MovieDAO;
import com.Proyecto.poyect1.persistence.model.MovieEntity;
import com.mysql.cj.exceptions.ExceptionFactory;

@Repository
public class MoviesImpl implements MoviesRepository {

    private final int LIMIT = 10;
    @Autowired
    MovieDAO movieDAO;

    @Override
    public int insert(Movie movie) {
        try (Connection connection = DBUtil.open(false)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            int id = movieDAO.insert(connection, movieEntity);
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    
    public List<Movie> findMovies(Integer page,Integer LIMIT) {
        try (Connection conn = DBUtil.open(true)) {
            List<MovieEntity> movieEntities =movieDAO.findMovies(page, LIMIT);
            List<Movie> movies =movieEntities.stream()
            .map(movieEntity -> MovieMapper.mapper.toMovie(movieEntity))
            .toList();
            return movies;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Movie> findMoviesById(int id) {
        try (Connection connection = DBUtil.open(true)) {
            Optional<MovieEntity> movieEntity =movieDAO.findMoviesById(id);
            if(movieEntity.isEmpty()){
                return Optional.empty();
            }
            return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Integer getTotalRecords() {
        try (Connection connection = DBUtil.open(true)) {
            return movieDAO.getTotalNumberOfRecords(connection);

        } catch (Exception e) {
            throw new RuntimeException("VA MAL");
        }
    }
    @Override
    public void deleteMovie(int id) {
        try (Connection connection = DBUtil.open(true)) {
        movieDAO.delete(id);
        } catch (Exception e) {
            System.out.println("insertMovies");
        }
    }
}
