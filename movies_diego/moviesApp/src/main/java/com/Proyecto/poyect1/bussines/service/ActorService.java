package com.Proyecto.poyect1.bussines.service;

import java.util.List;

import com.Proyecto.poyect1.bussines.entity.Actor;
import com.Proyecto.poyect1.bussines.entity.Director;

public interface ActorService {
    
    public int insertActor(Actor actor);

    public void update(Actor actor);
    
    public Actor findActor(int id); 

    public List<Actor> findActorByMovieId(int id);
}
