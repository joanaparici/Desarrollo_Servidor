package com.Proyecto.poyect1.bussines.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Actor {
    private String name;
    private int birthYear;
    private int deathYear;
    private int id;
    
    public Actor(String name, int yearBirth, int yearDeath) {
        this.name = name;
        this.birthYear = yearBirth;
        this.deathYear = yearDeath;
    }

    public Actor(String name, int yearBirth, int yearDeath, int id) {
        this.name = name;
        this.birthYear = yearBirth;
        this.deathYear = yearDeath;
        this.id = id;
    }
}
