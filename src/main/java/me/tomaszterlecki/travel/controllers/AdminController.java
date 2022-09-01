package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
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
    ICountriesService countriesService;
    @Autowired
    ICitiesService citiesService;
    @Autowired
    IEntitySaver entitySaver;

    @RequestMapping(value = "admin")
    public String adminPanel(Model model){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        model.addAttribute("user", sessionObject.getUser());
        return "/admin/admin-panel";
    }

    @RequestMapping(value = "edit/countries")
    public String editCountryPanel(Model model){
        model.addAttribute("countries", countriesService.getAllCountries());
        model.addAttribute("user", sessionObject.getUser());
        return "/admin/edit-countries";
    }
    @RequestMapping(value = "edit/country/{countryId}", method = RequestMethod.GET)
    public String editCountry(Model model, @PathVariable("countryId") int countryId){
        Country country = countriesService.getCountryByID(countryId);
        model.addAttribute(country);
        return "/admin/edit-country";
    }
    @RequestMapping(value="edit/country", method = RequestMethod.POST)
    public String editCountry(Model model, @ModelAttribute("country") Country country){
        entitySaver.updateEntity(country);
        return "redirect:/admin/edit_countries";
    }
    @RequestMapping(value = "delete/country/{countryId}", method = RequestMethod.GET)
    public String deleteCountry(Model model, @PathVariable("countryId") int countryId){
        Country country = countriesService.getCountryByID(countryId);
        model.addAttribute(country);
        return "/admin/edit-country";
    }
    @RequestMapping(value="delete/country/", method = RequestMethod.POST)
    public String deleteCountry(Model model, @ModelAttribute("country") Country country){
        entitySaver.deleteEntity(country);
        return "redirect:/admin/edit_countries";
    }
    @RequestMapping(value="edit/cities")
    public String editCityPanel(Model model){
        model.addAttribute("city", new City());
        model.addAttribute("elements", citiesService.getAllCitiesInAllCountries());
        return "/admin/edit-cities";
    }

    @RequestMapping(value = "edit/city/{cityId}", method = RequestMethod.GET)
    public String editCity(Model model, @PathVariable("cityId") int cityId){
        City city = citiesService.getCityById(cityId);
        model.addAttribute(city);
        return "/admin/edit-country";
    }

}
