package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.ICountriesService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/cities")
public class CityRestController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    ICitiesService citiesService;
    @Autowired
    ICountriesService countriesService;

    @RequestMapping(value="/{countryId}", method = RequestMethod.GET)
    public List<City> showCitiesForACountry(@PathVariable int countryId){
        Country country = countriesService.getCountryByID(countryId);
        List<City> result = new ArrayList<>();
        result = citiesService.getAllCitiesByCountry(country);
        return result;
    }
}
