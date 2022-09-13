package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.ICountriesService;
import me.tomaszterlecki.travel.services.IPicturesService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CountriesController {
    @Autowired
    ICitiesService citiesService;
    @Autowired
    ICountriesService countriesService;
    @Autowired
    IPicturesService picturesService;
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    SessionObject sessionObject;
    @Autowired
    IEntitySaver entitySaver;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public String displayCountries(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "index";
        }
//        List<CitiesForAGivenCountry> countries = citiesService.getCitiesInCountry();
        model.addAttribute("elements", sessionObject.getCitiesForAGivenCountries());
        authenticationService.addCommonInfoToModel(model);
        return "countries";
    }

    @RequestMapping(value = "/countries/{id}", method = RequestMethod.GET)
    public String displayCities(Model model, @PathVariable int id) {
        City city = citiesService.getCityById(id);
        model.addAttribute("city", city);
        model.addAttribute("elements", picturesService.getDatesForACity(city));
        authenticationService.addCommonInfoToModel(model);
        return "cities";
    }

}


