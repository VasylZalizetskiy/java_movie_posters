package ua.pp.movie_posters;

import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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
import ua.pp.movie_posters.webapp.services.impl.MovieServiceRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
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
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MovieRepository movieRepository;

    @Mock
    MovieService movieService;

    @InjectMocks
    private AppController appController;

    Movie movie = new Movie("test", 2018, "test");

    @Test
    public void accessUnsecuredResourceThenOk() throws Exception {
//        when(movieService.getAllMovies()).thenReturn(movies);

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("admin")
                .password("password");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("admin"));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("invalid")
                .password("password");

        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }

    @Test
    public void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mockMvc.perform(post("/")
                .param("name", movie.getName())
                .param("year", String.valueOf(movie.getYear()))
                .param("image", movie.getImage()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        mockMvc.perform(post("/")
                .param("name", movie.getName())
                .param("year", String.valueOf(movie.getYear()))
                .param("image", movie.getImage()))
                .andExpect(status().isOk());
    }

}