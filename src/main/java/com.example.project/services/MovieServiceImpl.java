package com.example.project.services;
import com.example.project.models.Movie;
import com.example.project.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    @Override
    public Movie getMovieById(String id) {
        return movieRepository.findOne(id);
    }
    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
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