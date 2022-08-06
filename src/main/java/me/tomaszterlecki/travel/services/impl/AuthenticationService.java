package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.IUserDAO;
import me.tomaszterlecki.travel.model.User;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationService implements IAuthenticationService {
    @Autowired
    IUserDAO iUserDaDAO;

    @Override
    public void authenticate(User user) {

    }

    @Override
    public void register(User user) {

    }

    @Override
    public void logout() {

    }
}
