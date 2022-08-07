package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.model.User;
import me.tomaszterlecki.travel.services.IAuthenticationService;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login (Model model){
        model.addAttribute("userModel", new User());
        return "login";
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
