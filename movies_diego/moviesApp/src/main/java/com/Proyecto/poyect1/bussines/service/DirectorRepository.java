package com.Proyecto.poyect1.bussines.service;

import java.util.Optional;

import com.Proyecto.poyect1.bussines.entity.Director;

public interface DirectorRepository {

    public int insert(Director director);

    public void update(Director director);

    public  Optional<Director> findDirector(int id);

    public Optional<Director> findDirectorByMovieId(int id);

    public void delete(int id);
}
