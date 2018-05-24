package ua.pp.movie_posters.webapp.services;

import ua.pp.movie_posters.webapp.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByName(String name);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
