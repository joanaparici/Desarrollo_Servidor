package com.Proyecto.poyect1.bussines.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Director {
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;


    public Director(int id ,String name, int yearBirth, Integer yearDeath) {
        this.id=id;
        this.name = name;
        this.birthYear = yearBirth;
        this.deathYear = yearDeath;
    }
    
    public Director(String name, int yearBirth, Integer yearDeath) {
        this.name = name;
        this.birthYear = yearBirth;
        this.deathYear = yearDeath;
    }

    @Override
    public String toString() {
        return "Director [id=" + id + ", name=" + name + ", yearBirth=" + birthYear + ", yearDeath=" + deathYear + "]";
    }


}
