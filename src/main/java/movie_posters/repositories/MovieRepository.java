package movie_posters.repositories;
import movie_posters.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie,String>{

    @Query(value = "{'releasedYear': {$eq: ?0} }")
    List<Movie> findByYear(int year);

}