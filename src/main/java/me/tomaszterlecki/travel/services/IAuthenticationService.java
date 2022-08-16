package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.User;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(User user);
    void logout();

}
