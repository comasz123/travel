package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.ICountriesService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    SessionObject sessionObject;
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    ICountriesService countriesService;
    @Autowired
    ICitiesService citiesService;


    @RequestMapping(value = "admin")
    public String adminPanel(Model model){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);

        return "/admin/admin-panel";
    }

    @RequestMapping(value = "edit/countries")
    public String editCountryPanel(Model model){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);

        model.addAttribute("countries", countriesService.getAllCountries());
        return "/admin/edit-countries";
    }

    @RequestMapping(value="edit/cities",method = RequestMethod.GET)
    public String editCityPanel(Model model){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);

        model.addAttribute("elements", citiesService.getAllCitiesInAllCountries());
        return "/admin/edit-cities";
    }


}
