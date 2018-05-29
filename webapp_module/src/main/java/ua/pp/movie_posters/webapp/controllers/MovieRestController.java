package ua.pp.movie_posters.webapp.controllers;

import ua.pp.movie_posters.models.Movie;
import ua.pp.movie_posters.webapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {
    @Autowired
    @Qualifier("MovieServiceRepositoryImpl")
    private MovieService movieService;
    @ResponseBody
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    @ResponseBody
    @GetMapping(value = "/{name}")
    public Movie getMovieByName(@PathVariable String name) {
        return movieService.getMovieByName(name);
    }
    @ResponseBody
    @PostMapping
    public String addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return "Created";
    }
    @ResponseBody
    @PutMapping
    public String updateMovie(@RequestBody Movie movie) {
        movieService.updateMovie(movie);
        return "Updated";
    }
    @ResponseBody
    @DeleteMapping(value = "/{name}")
    public String deleteMovie(@PathVariable String name) {
        Movie movie = movieService.getMovieByName(name);
        movieService.deleteMovie(movie);
        return "Deleted";
    }
}