package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.ICountriesService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EditObjectsController {
    @Autowired
    SessionObject sessionObject;
    @Autowired
    ICountriesService countriesService;
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    IEntitySaver entitySaver;
    @Autowired
    ICitiesService citiesService;

    @RequestMapping(value = "edit/country/{countryId}", method = RequestMethod.GET)
    public String editCountry(Model model, @PathVariable("countryId") int countryId){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);

        Country country = countriesService.getCountryByID(countryId);
        model.addAttribute(country);
        return "/admin/edit-country";
    }
    @RequestMapping(value="edit/country", method = RequestMethod.POST)
    public String editCountry(Model model, @ModelAttribute("country") Country country){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);

        entitySaver.updateEntity(country);
        return "redirect:/edit/countries";
    }
    @RequestMapping(value = "delete/country/{countryId}", method = RequestMethod.GET)
    public String deleteCountry(Model model, @PathVariable("countryId") int countryId){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);

        Country country = countriesService.getCountryByID(countryId);
        entitySaver.deleteEntity(country);
        return "redirect:/edit/countries";
    }
    @RequestMapping(value = "edit/city", method = RequestMethod.GET)
    public String editCity(Model model, @RequestParam("cityId") int cityId){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);
        
        City city = citiesService.getCityById(cityId);
        model.addAttribute("countries", countriesService.getAllCountries());
        model.addAttribute(city);
        return "/admin/edit-city";
    }
    @RequestMapping(value = "edit/city", method = RequestMethod.POST)
    public String editCity(Model model, @ModelAttribute City city, @RequestParam("countryId") int countryId){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);

        Country country = countriesService.getCountryByID(countryId);
        city.setCountry(country);
        entitySaver.updateEntity(city);

        return "redirect:/admin";
    }


}
