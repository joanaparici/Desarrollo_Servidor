package com.Proyecto.poyect1.controller.model.movie;

import java.util.List;

import com.Proyecto.poyect1.controller.model.actor.ActorListWeb;
import com.Proyecto.poyect1.controller.model.director.DirectorListWeb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class MovieDetailWeb {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorListWeb director;
    private List<ActorListWeb> actors;
}
