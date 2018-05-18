package movie_posters.services;

import movie_posters.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    List<Movie> getAllMovies(int year);

    Movie getMovieById(String id);

    Movie addMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(String id);
}
