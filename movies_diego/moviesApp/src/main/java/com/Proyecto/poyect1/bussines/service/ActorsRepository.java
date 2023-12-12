package com.Proyecto.poyect1.bussines.service;

import java.util.List;
import java.util.Optional;

import com.Proyecto.poyect1.bussines.entity.Actor;

public interface ActorsRepository {

    public int  insertActor(Actor actor);

    public void update(Actor actor);

    public Optional<Actor> findActor(int id);

    public List<Actor> findActorByMovieId(int id);

}    

    
    

