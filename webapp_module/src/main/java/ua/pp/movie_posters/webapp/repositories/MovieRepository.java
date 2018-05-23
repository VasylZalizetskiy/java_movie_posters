package ua.pp.movie_posters.webapp.repositories;
import ua.pp.movie_posters.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie,String>{

    @Query(value = "{'year': {$eq: ?0} }")
    List<Movie> findByYear(int year);

    @Query(value = "{'name': {$eq: ?0} }")
    Movie findByName(String name);
}