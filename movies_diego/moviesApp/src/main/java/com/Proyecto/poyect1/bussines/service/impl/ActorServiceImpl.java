package com.Proyecto.poyect1.bussines.service.impl;

import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.exceptions.ResourceNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto.poyect1.bussines.entity.Actor;
import com.Proyecto.poyect1.bussines.service.ActorService;
import com.Proyecto.poyect1.bussines.service.ActorsRepository;

@Service
public class ActorServiceImpl implements ActorService {
@Autowired
ActorsRepository actorsRepository;

    @Override
    public int insertActor(Actor actor) {
        return actorsRepository.insertActor(actor);
        
    }
    @Override
    public void update(Actor actor) {
        Actor existingActor= actorsRepository.findActor(actor.getId()).orElseThrow(()->new ResourceNotFoundException("Actor no encontrado"));
        actorsRepository.update(actor);
        
    }
    @Override
    public Actor findActor(int id) {
        return actorsRepository.findActor(id).orElseThrow(()->new ResourceNotFoundException("Actor no encontrado"));
    }
    @Override
    public List<Actor> findActorByMovieId(int id) {
        return actorsRepository.findActorByMovieId(id);
    }

}
