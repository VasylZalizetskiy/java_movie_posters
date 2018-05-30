package ua.pp.movie_posters.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.pp.movie_posters.models.Movie;
import ua.pp.movie_posters.webapp.configs.MongoConfig;
import ua.pp.movie_posters.webapp.repositories.MovieRepository;
import ua.pp.movie_posters.webapp.services.impl.MovieServiceRepositoryImpl;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApp.class, MongoConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceRepositoryImpl movieService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Movie movie1 = new Movie("test2", 2018, "test2");
        Movie movie2 = new Movie("test3", 2018, "test3");

        this.movieRepository.save(movie1);
        this.movieRepository.save(movie2);
    }

    @Test
    public void apiTest() throws Exception {

        this.mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("test2")))
                .andExpect(jsonPath("$[0].year", is(2018)))
                .andExpect(jsonPath("$[0].image", is("test2")));
    }

    @After
    public void tearDown() throws Exception {
        this.movieRepository.deleteAll();
    }
}