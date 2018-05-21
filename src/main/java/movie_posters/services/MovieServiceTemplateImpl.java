package movie_posters.services;

import movie_posters.models.Movie;
import movie_posters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MovieServiceTemplateImpl")
public class MovieServiceTemplateImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Movie> getAllMovies(int year){
        return movieRepository.findByYear(year);
    }

    @Override
    public List<Movie> getAllMovies(){
        return mongoTemplate.findAll(Movie.class);
    }
    @Override
    public Movie getMovieById(String id) {
        return movieRepository.findOne(id);
    }
    @Override
    public void addMovie(Movie movie) {
        mongoTemplate.save(movie, "movie");
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