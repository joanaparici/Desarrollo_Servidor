package com.Proyecto.poyect1.controller.model.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieCreateWeb {
        private String title;
        private int year;
        private int runtime;
        private int directorId;
        //private List<CharacterMovieCreateWeb> characterMovieCreateWebs;
    }

