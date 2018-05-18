package movie_posters.controller;

import movie_posters.models.Movie;
import movie_posters.services.MovieService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    MovieService movieService;

   @GetMapping("/")
   public String index(ModelMap model) {
       List<Movie> movieList = movieService.getAllMovies();
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