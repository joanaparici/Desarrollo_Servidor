package com.Proyecto.poyect1.bussines.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.bussines.service.DirectorRepository;
import com.Proyecto.poyect1.bussines.service.DirectorService;
import com.Proyecto.poyect1.exceptions.ResourceNotFoundException;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    DirectorRepository directorRepository;

    @Override
    public int insert(Director director) {
        return directorRepository.insert(director);
    }

    @Override
    public void update(Director director) {
        Director existingDirector = directorRepository.findDirector(director.getId()).orElseThrow(()->new ResourceNotFoundException("Director no encontrado"));
        directorRepository.update(director);
    }


    public void delete(int id) {
        Director director = directorRepository.findDirector(id).orElseThrow(()->new ResourceNotFoundException("Director no encontrado"));
        if (director == null) {
            throw new ResourceNotFoundException("Director not found with id: " + id);
        }
        directorRepository.delete(id);
    }

    @Override
    public Director findDirector(int id) {
        return directorRepository.findDirector(id).orElseThrow(()->new ResourceNotFoundException("Director no encontrado"));
        
    }
    public Director findDirectorByMovieId(int id){
        return directorRepository.findDirectorByMovieId(id).orElseThrow(()->new ResourceNotFoundException("Director no encontrado"));
    }
}
