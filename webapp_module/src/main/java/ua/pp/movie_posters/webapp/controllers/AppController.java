package ua.pp.movie_posters.webapp.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.pp.movie_posters.models.Movie;
import ua.pp.movie_posters.webapp.services.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AppController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    @Qualifier("MovieServiceRepositoryImpl")
    MovieService movieService;

/*
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public AppController(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }
*/

    @GetMapping("/")
    public String getAllMovies(@RequestParam(name="year", required=false) Integer year, ModelMap model) {
        List<Movie> movieList = (year != null) ? movieService.getAllMovies(year) : movieService.getAllMovies();
        model.addAttribute("movieList", movieList);
        return "index";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @RequestMapping(value = "/denied")
    public String accessDenied() { return "denied"; }

    @GetMapping(value="/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) { new SecurityContextLogoutHandler().logout(request, response, auth); }
        return "redirect:/login?logout";
    }

    @PostMapping("/")
    @Secured({ "ROLE_ADMIN" })
    public String addMovie(Movie movie, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        movieService.addMovie(movie);
//      rabbitTemplate.convertAndSend(MainApp.topicExchangeName, "foo.bar.baz", "Movie: "+movie.getName()+" are created!");
        List<Movie> movieList = movieService.getAllMovies();
        model.addAttribute("movieList", movieList);

        return "index";
    }

    @GetMapping("/remove")
    @Secured({ "ROLE_ADMIN" })
    public String removeMovie(@RequestParam(name="name") String name) {
        Movie movie = movieService.getMovieByName(name);
        movieService.deleteMovie(movie);
        return "redirect:/";
    }
}