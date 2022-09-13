package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.exceptions.ValidationException;
import me.tomaszterlecki.travel.model.database.User;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.session.SessionObject;
import me.tomaszterlecki.travel.validators.UserDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login (Model model){
        authenticationService.addCommonInfoToModel(model);
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password, Model model) {
        try {
            UserDataValidator.validateLogin(login);
            UserDataValidator.validatePassword(password);
        } catch (ValidationException e) {
            return "redirect:/login";
        }
        authenticationService.authenticate(login, password);
        authenticationService.addCommonInfoToModel(model);
        if(this.sessionObject.isLogged()) {
            System.out.println(login);
            return "index";
        }
        return "redirect:/login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("userModel", new User());
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register2(@ModelAttribute User user, @RequestParam String password2, @RequestParam String status) {
        authenticationService.register(user);
        return "redirect:/";
    }

}
