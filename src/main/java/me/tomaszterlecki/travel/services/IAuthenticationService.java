package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.database.User;
import org.springframework.ui.Model;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(User user);
    void addCommonInfoToModel(Model model);
    void logout();

}
