package ua.pp.movie_posters.webapp.services;

import ua.pp.movie_posters.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    List<Movie> getAllMovies(int year);

    Movie getMovieByName(String name);

    void addMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovie(Movie movie);
}
