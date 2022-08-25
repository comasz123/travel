package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.IPicturesService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class MainController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    ICitiesService citiesService;
    @Autowired
    IPicturesService picturesService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        this.authenticationService.authenticate("karol", "karol");
        this.authenticationService.addCommonInfoToModel(model);
        citiesService.setCountriesAndCitiesForUser();
        sessionObject.setYearsTravelled(picturesService.yearsAndMonths());
        return "index";
    }

}
