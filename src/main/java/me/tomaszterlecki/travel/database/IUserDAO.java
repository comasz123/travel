package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.User;

import java.util.Optional;

public interface IUserDAO {
    User getUserById(int id);
    Optional<User> getUserByLogin(String login);
}
