package com.Proyecto.poyect1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Proyecto.poyect1.controller.model.movie.MovieCreateWeb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.Proyecto.poyect1.bussines.entity.Movie;
import com.Proyecto.poyect1.controller.model.movie.MovieDetailWeb;
import com.Proyecto.poyect1.controller.model.movie.MovieListWeb;
import com.Proyecto.poyect1.persistence.model.MovieEntity;

@Mapper(componentModel="spring")
public interface MovieMapper {
  MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

  MovieListWeb toMovieListWeb(Movie movie);
  MovieDetailWeb toMovieDetailWeb(Movie movie);
    

  @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
  @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
  @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
  @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
  @Mapping(target = "directorId", expression = "java(resultSet.getInt(\"director_id\"))")
  MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;
  MovieEntity toMovieEntity(Movie movie);
  Movie toMovie(MovieEntity movieEntity);

  @Mapping(target = "director", ignore = true)
  Movie toMovie(MovieCreateWeb movieCreateWeb);
} 

