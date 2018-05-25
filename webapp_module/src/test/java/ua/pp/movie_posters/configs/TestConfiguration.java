package ua.pp.movie_posters.configs;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ua.pp.movie_posters.webapp.services.MovieService;
import ua.pp.movie_posters.webapp.services.UserService;
import ua.pp.movie_posters.webapp.services.impl.MovieServiceRepositoryImpl;
import ua.pp.movie_posters.webapp.services.impl.UserServiceImpl;

@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public UserService userService() {
        return Mockito.mock(UserServiceImpl.class);
    }

    @Bean
    @Primary
    public MovieService movieService() {
        return Mockito.mock(MovieServiceRepositoryImpl.class);
    }
}
