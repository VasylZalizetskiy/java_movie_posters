package movie_posters.controllers;

import movie_posters.models.Movie;
import movie_posters.services.MovieService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    @Qualifier("MovieServiceTemplateImpl")
    MovieService movieService;

    @GetMapping("/")
    public String listByYear(@RequestParam(name="year", required=false) Integer year, ModelMap model) {
        List<Movie> movieList = (year != null) ? movieService.getAllMovies(year) : movieService.getAllMovies();

        model.addAttribute("movieList", movieList);

        return "index";
    }

    @PostMapping("/")
    public String addMovie(Movie movie, ModelMap model) {
        movieService.addMovie(movie);
        List<Movie> movieList = movieService.getAllMovies();
        model.addAttribute("movieList", movieList);

        return "index";
    }
}