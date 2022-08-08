package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.ICountriesService;
import me.tomaszterlecki.travel.services.IPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CountriesController {
    @Autowired
    ICitiesService citiesService;
    @Autowired
    IPicturesService picturesService;

    @RequestMapping(value="/countries", method = RequestMethod.GET)
    public String displayCountries(Model model){
        List<CitiesForAGivenCountry> countries = citiesService.getCitiesInCountry();
        model.addAttribute("elements", countries);
        return "countries";
    }
    @RequestMapping(value="/countries/{id}", method = RequestMethod.GET)
    public String displayCities(Model model, @PathVariable int id){
        City city = citiesService.getCityById(id);
        model.addAttribute("city", city);
        model.addAttribute("elements", picturesService.getDatesForACity(city));
        return "cities";
    }

}
