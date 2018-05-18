package movie_posters.services;
import movie_posters.models.Movie;
import movie_posters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
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