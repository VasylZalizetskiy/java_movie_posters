package ua.pp.movie_posters;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.pp.movie_posters.models.Movie;
import ua.pp.movie_posters.webapp.MainApp;
import ua.pp.movie_posters.webapp.controllers.AppController;
import ua.pp.movie_posters.webapp.controllers.MovieRestController;
import ua.pp.movie_posters.webapp.repositories.MovieRepository;
import ua.pp.movie_posters.webapp.services.MovieService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)
@AutoConfigureMockMvc
public class RestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MovieRepository movieRepository;

    @Mock
    MovieService movieService;

    @InjectMocks
    private MovieRestController movieRestController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(movieRestController)
                .build();
    }

    @Test
    public void apiTest() throws Exception {
        List<Movie> movies = Lists.newArrayList(
                new Movie("test", 2018, "test"),
                new Movie("test2", 2018, "test2")
        );

        when(movieService.getAllMovies()).thenReturn(movies);

        this.mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("test")))
                .andExpect(jsonPath("$[0].year", is(2018)))
                .andExpect(jsonPath("$[0].image", is("test")));

        verify(movieService, times(1)).getAllMovies();
        verifyNoMoreInteractions(movieService);
    }
}