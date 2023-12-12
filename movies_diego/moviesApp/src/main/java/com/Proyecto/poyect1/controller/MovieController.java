package com.Proyecto.poyect1.controller;

import java.util.List;

import com.Proyecto.poyect1.controller.model.movie.MovieCreateWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.Proyecto.poyect1.bussines.entity.Movie;
import com.Proyecto.poyect1.bussines.service.DirectorService;
import com.Proyecto.poyect1.bussines.service.MoviesService;
import com.Proyecto.poyect1.controller.model.movie.MovieListWeb;
import com.Proyecto.poyect1.http_response.Response;
import com.Proyecto.poyect1.mapper.MovieMapper;


@RequestMapping(MovieController.MOVIES)
@RestController
public class MovieController {

        public static final String MOVIES = "/movies";

       @Autowired 
        MoviesService moviesService;
        @Autowired
        DirectorService directorService;



        @ResponseStatus(HttpStatus.OK)
        @GetMapping("")
        public Response findMovies(@RequestParam(required = false, defaultValue = "1") Integer page,
                        @RequestParam(required = false, defaultValue = "10") Integer LIMIT) {

                List<Movie> movies = moviesService.findMovies(page, LIMIT);

                List<MovieListWeb>moviesWeb=movies.stream()            //ESTO TRANSFORMA LOS OBJETOS MOVIE A MovieListWeb
                .map(movie -> MovieMapper.mapper.toMovieListWeb(movie))    
                .toList();                                             //LO TRANSFORMAMOS EN UNA LISTA
                //Con esto modificamos la respuesta que nos daba, pasamos de mostrar todos los atributos de la entidad movie a...
                //los atributos especificos que hemos creado en MovieListWeb.

                int totalRecords = moviesService.getTotalRecords();
                //Almacenamos en la variable totalRecords todos los registros de movies de la bbdd
                
                Response response = Response.builder()
                                .data(moviesWeb)
                                .totalRecords(totalRecords)
                                .build();
                return response;

        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("/{id}")
        public Response find(@PathVariable("id") int id) {
                return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(moviesService.findMoviesById(id))).build();//PEGUNTAR POR QUE ES DIFERENTE AL DE ARRIBA
        
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")

        public void delete(@PathVariable("id") int id) {
        moviesService.deleteMovie(id);
}


        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping("")
        public Response insert(@RequestBody MovieCreateWeb movieCreateWeb) {
            int id = moviesService.insert(
                MovieMapper.mapper.toMovie(movieCreateWeb),
                movieCreateWeb.getDirectorId()
        );
        MovieListWeb movieListWeb = new MovieListWeb();
        movieListWeb.setTitle(movieCreateWeb.getTitle());
        movieListWeb.setId(id);
        return Response.builder().data(movieListWeb).build();
    }
}
