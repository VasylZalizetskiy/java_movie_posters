package ua.pp.movie_posters.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.pp.movie_posters.models.Movie;
import ua.pp.movie_posters.webapp.models.User;
import ua.pp.movie_posters.webapp.repositories.MovieRepository;
import ua.pp.movie_posters.webapp.repositories.UserRepository;

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

    private Movie testMovie = new Movie("test", 2018, "test");

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Before
    public void setUp() throws Exception {
        User user= new User();
        user.setUsername("testUser");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.addRole("ROLE_USER");

        User admin= new User();
        admin.setUsername("testAdmin");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setEnabled(true);
        admin.setAccountNonExpired(true);
        admin.setAccountNonLocked(true);
        admin.setCredentialsNonExpired(true);
        admin.addRole("ROLE_ADMIN");

        this.userRepository.save(user);
        this.userRepository.save(admin);
    }

    @Test
    public void accessUnsecuredResourceThenOk() throws Exception {

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

    @Test
    public void loginAvailableForAll() throws Exception {
        mockMvc
                .perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        mockMvc
                .perform(formLogin().user("testUser").password("password"))
                .andExpect(authenticated().withUsername("testUser"));
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
                .param("name", testMovie.getName())
                .param("year", String.valueOf(testMovie.getYear()))
                .param("image", testMovie.getImage()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        mockMvc.perform(post("/")
                .param("name", testMovie.getName())
                .param("year", String.valueOf(testMovie.getYear()))
                .param("image", testMovie.getImage()))
                .andExpect(status().isOk());
    }

    @After
    public void tearDown() throws Exception {
        this.userRepository.deleteAll();
        this.movieRepository.deleteAll();
    }

}