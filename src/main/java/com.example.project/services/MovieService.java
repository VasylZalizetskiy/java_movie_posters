package com.example.project.services;

import com.example.project.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(String id);

    Movie addMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(String id);
}
