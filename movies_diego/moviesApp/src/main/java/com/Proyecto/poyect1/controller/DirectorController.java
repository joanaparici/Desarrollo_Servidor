 package com.Proyecto.poyect1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.bussines.service.DirectorService;
import com.Proyecto.poyect1.controller.model.director.DirectorCreateWeb;
import com.Proyecto.poyect1.controller.model.director.DirectorDetailWeb;
import com.Proyecto.poyect1.controller.model.director.DirectorUpdateWeb;
import com.Proyecto.poyect1.http_response.Response;
import com.Proyecto.poyect1.mapper.DirectorMapper;

@RequestMapping("/directors")
@RestController
public class DirectorController {
    @Autowired
    DirectorService directorService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response insert(@RequestBody DirectorCreateWeb directorCreateWeb) {
        int id=directorService.insert(DirectorMapper.mapper.toDirector(directorCreateWeb));
    DirectorDetailWeb directorDetailWeb = new DirectorDetailWeb(
            id,
            directorCreateWeb.getName(),
            directorCreateWeb.getBirthYear(),
            directorCreateWeb.getDeathYear()
    );
    return Response.builder().data(directorDetailWeb).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id,@RequestBody DirectorUpdateWeb directorUpdateWeb){
        directorUpdateWeb.setId(id);
        directorService.update(DirectorMapper.mapper.toDirector(directorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }
    @ResponseStatus(HttpStatus.OK)
    
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
    return Response.builder().data(DirectorMapper.mapper.toDirectorDetailWeb(directorService.findDirector(id))).build();
}
}
