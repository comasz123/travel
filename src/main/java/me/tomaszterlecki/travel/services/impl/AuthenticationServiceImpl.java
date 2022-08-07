package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.model.User;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    IEntitySaver saver;

    @Override
    public void authenticate(User user) {

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
