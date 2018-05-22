package ua.pp.movie_posters.webapp.services;

import ua.pp.movie_posters.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    List<Movie> getAllMovies(int year);

    Movie getMovieById(String id);

    void addMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(String id);
}
