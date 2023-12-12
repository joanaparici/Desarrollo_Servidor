package com.Proyecto.poyect1.bussines.service;

import com.Proyecto.poyect1.bussines.entity.Director;

public interface DirectorService {

     public int insert(Director director);

     public void update(Director director);

     public void delete(int id);

     public Director findDirector(int id);

     public Director findDirectorByMovieId(int id);
}
