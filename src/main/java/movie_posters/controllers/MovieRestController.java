package movie_posters.controllers;
import movie_posters.models.Movie;
import movie_posters.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MovieRestController {
    @Autowired
    @Qualifier("MovieServiceTemplateImpl")
    private MovieService movieService;
    @ResponseBody
    @RequestMapping(value = "/api/movies", method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    @ResponseBody
    @RequestMapping(value = "/api/movies/{id}", method = RequestMethod.GET)
    public Movie getMovieByName(@PathVariable String id) {
        return movieService.getMovieById(id);
    }
    @ResponseBody
    @RequestMapping(value = "/api/movies", method = RequestMethod.POST)
    public void addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
    }
    @ResponseBody
    @RequestMapping(value = "/api/movies", method = RequestMethod.PUT)
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }
    @ResponseBody
    @RequestMapping(value = "/api/movies/{id}", method = RequestMethod.DELETE)
    public String deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return "Deleted";
    }
}