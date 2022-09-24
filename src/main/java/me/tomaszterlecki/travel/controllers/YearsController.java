package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.services.IPicturesService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class YearsController {
    @Autowired
    IPicturesService picturesService;
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    SessionObject sessionObject;

    @RequestMapping(value="/years", method = RequestMethod.GET)
    public String listYears(Model model){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }

        model.addAttribute("elements", sessionObject.getYearsTravelled());
        authenticationService.addCommonInfoToModel(model);
        return "years";
    }
    @RequestMapping(value="/years/{year}/{month}", method = RequestMethod.GET)
    public String getCitiesForYear(Model model, @PathVariable int year, @PathVariable String month){

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        List<City> cities = picturesService.getAllCitiesForADate(year, month);
        model.addAttribute("cities", cities);
        authenticationService.addCommonInfoToModel(model);
        return "yearsAndCities";
    }
}
