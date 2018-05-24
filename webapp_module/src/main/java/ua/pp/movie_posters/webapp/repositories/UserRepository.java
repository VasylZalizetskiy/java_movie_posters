package ua.pp.movie_posters.webapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.pp.movie_posters.webapp.models.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

}
