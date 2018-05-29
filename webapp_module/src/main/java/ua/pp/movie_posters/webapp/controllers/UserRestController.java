package ua.pp.movie_posters.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import ua.pp.movie_posters.webapp.models.User;
import ua.pp.movie_posters.webapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseBody
    @GetMapping(value = "/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @ResponseBody
    @PostMapping
    public String addUser(@RequestBody(required=false) User reqUser) {
        User user = (reqUser != null) ? reqUser : new User("user", "password") ;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.addRole("ROLE_USER");
        user.addRole("ROLE_ADMIN");

        userService.addUser(user);
        return "Created";
    }

    @ResponseBody
    @PutMapping
    public String updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return "Updated";
    }

    @ResponseBody
    @DeleteMapping(value = "/{name}")
    public String deleteUser(@PathVariable String name) {
        User user = userService.getUserByName(name);
        userService.deleteUser(user);
        return "Deleted";
    }
}
