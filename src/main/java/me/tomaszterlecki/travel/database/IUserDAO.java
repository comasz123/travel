package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.User;

public interface IUserDAO {
    User getUserById(int id);
}
