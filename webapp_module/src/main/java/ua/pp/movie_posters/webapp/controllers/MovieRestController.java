package ua.pp.movie_posters.webapp.controllers;

import ua.pp.movie_posters.models.Movie;
import ua.pp.movie_posters.webapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieRestController {
    @Autowired
    @Qualifier("MovieServiceRepositoryImpl")
    private MovieService movieService;
    @ResponseBody
    @GetMapping(value = "/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    @ResponseBody
    @GetMapping(value = "/movies/{name}")
    public Movie getMovieByName(@PathVariable String name) {
        return movieService.getMovieByName(name);
    }
    @ResponseBody
    @PostMapping(value = "/movies")
    public String addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return "Created";
    }
    @ResponseBody
    @PutMapping(value = "/movies")
    public String updateMovie(@RequestBody Movie movie) {
        movieService.updateMovie(movie);
        return "Updated";
    }
    @ResponseBody
    @DeleteMapping(value = "/movies/{name}")
    public String deleteMovie(@PathVariable String name) {
        Movie movie = movieService.getMovieByName(name);
        movieService.deleteMovie(movie);
        return "Deleted";
    }
}