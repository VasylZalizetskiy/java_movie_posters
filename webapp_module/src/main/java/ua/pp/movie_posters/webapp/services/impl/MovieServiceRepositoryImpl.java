package ua.pp.movie_posters.webapp.services.impl;
import ua.pp.movie_posters.models.Movie;
import ua.pp.movie_posters.webapp.repositories.MovieRepository;
import ua.pp.movie_posters.webapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("MovieServiceRepositoryImpl")
public class MovieServiceRepositoryImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(int year){
        return movieRepository.findByYear(year);
    }

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    @Override
    public Movie getMovieById(String id) {
        return movieRepository.findOne(id);
    }

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }
    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    @Override
    public void deleteMovie(String id) {
        movieRepository.delete(id);
    }
}