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
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    @ResponseBody
    @RequestMapping(value = "/movies/{name}", method = RequestMethod.GET)
    public Movie getMovieByName(@PathVariable String name) {
        return movieService.getMovieByName(name);
    }
    @ResponseBody
    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public String addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return "Created";
    }
    @ResponseBody
    @RequestMapping(value = "/movies", method = RequestMethod.PUT)
    public String updateMovie(@RequestBody Movie movie) {
        movieService.updateMovie(movie);
        return "Updated";
    }
    @ResponseBody
    @RequestMapping(value = "/movies/{name}", method = RequestMethod.DELETE)
    public String deleteMovie(@PathVariable String name) {
        Movie movie = movieService.getMovieByName(name);
        movieService.deleteMovie(movie);
        return "Deleted";
    }
}