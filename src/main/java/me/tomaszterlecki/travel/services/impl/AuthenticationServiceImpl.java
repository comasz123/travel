package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.database.IUserDAO;
import me.tomaszterlecki.travel.model.User;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    IEntitySaver saver;
    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionObject sessionObject;
    @Override
    public void authenticate(String login, String password) {
        Optional<User> userToAuthenticate = this.userDAO.getUserByLogin(login);
        if(userToAuthenticate.isPresent() &&
                userToAuthenticate.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setUser(userToAuthenticate.get());
        }
    }

    @Override
    public void register(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.saver.persistEntity(user);
    }

    @Override
    public void logout() {

    }
}
