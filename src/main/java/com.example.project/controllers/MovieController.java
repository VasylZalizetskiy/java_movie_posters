package com.example.project.controllers;
import com.example.project.models.Movie;
import com.example.project.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;
    @ResponseBody
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    @ResponseBody
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public Movie getMovieByName(@PathVariable String id) {
        return movieService.getMovieById(id);
    }
    @ResponseBody
    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }
    @ResponseBody
    @RequestMapping(value = "/movies", method = RequestMethod.PUT)
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }
    @ResponseBody
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public String deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return "Deleted";
    }
}