package com.Proyecto.poyect1.controller;

import com.Proyecto.poyect1.bussines.entity.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.Proyecto.poyect1.bussines.entity.Actor;
import com.Proyecto.poyect1.bussines.service.ActorService;
import com.Proyecto.poyect1.http_response.Response;
import com.Proyecto.poyect1.mapper.ActorMapper;
import com.Proyecto.poyect1.mapper.DirectorMapper;

@RequestMapping("/actors")
@RestController

public class ActorController {
    @Autowired
    ActorService actorService;


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public void insert( @RequestBody Actor actor) {

        actorService.insertActor(actor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Actor actor) {
        actor.setId(id);
        actorService.update(actor);
    }


//     @ResponseStatus(HttpStatus.NO_CONTENT)
//     @GetMapping("/{id}")
//     public Response find(@PathVariable("id") int id) {
//         return Response.builder().data(ActorMapper.mapper.toActorListWeb(actorService.findActor(id))).build();
// }
}
