package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.User;

public interface IAuthenticationService {
    void authenticate(User user);
    void register(User user);
    void logout();

}
