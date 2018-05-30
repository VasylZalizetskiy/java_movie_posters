package ua.pp.movie_posters.webapp.configs;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "ua.pp.movie_posters.webapp.repositories")
public class MongoConfig {

    @Value( "${mongodb.host}" )
    private String host;

    @Value( "${mongodb.port}" )
    private int port;

    @Value( "${mongodb.database}" )
    private String db;

    @Bean
    public MongoClient mongo() throws Exception {
        return new MongoClient(host, port);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), db);
    }
}