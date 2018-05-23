package ua.pp.movie_posters.webapp.services.impl;

import ua.pp.movie_posters.models.Movie;
import ua.pp.movie_posters.webapp.repositories.MovieRepository;
import ua.pp.movie_posters.webapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MovieServiceTemplateImpl")
public class MovieServiceTemplateImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Movie> getAllMovies(int year) {
        Query query = new Query();
        query.addCriteria(Criteria.where("year").is(year));
        return mongoTemplate.find(query, Movie.class);
    }

    @Override
    public List<Movie> getAllMovies() {
        return mongoTemplate.findAll(Movie.class);
    }

    @Override
    public Movie getMovieByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
       return mongoTemplate.findOne(query, Movie.class);
    }

    @Override
    public void addMovie(Movie movie) {
        mongoTemplate.save(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        mongoTemplate.save(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        mongoTemplate.remove(movie);
    }
}