package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Picture;
import me.tomaszterlecki.travel.services.IPicturesService;
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

    @RequestMapping(value="/years", method = RequestMethod.GET)
    public String listYears(Model model){
        model.addAttribute("elements", picturesService.yearsAndMonths());
        return "years";
    }
    @RequestMapping(value="/years/{year}/{month}", method = RequestMethod.GET)
    public String getCitiesForYear(Model model, @PathVariable int year, @PathVariable String month){
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        List<City> cities = picturesService.getAllCitiesForADate(year, month);
        model.addAttribute("cities", cities);
        return "yearsAndCities";
    }
    @RequestMapping(value="/years/{year}/{month}/{city}", method = RequestMethod.GET)
    public String picturesForADate(Model model,
                                   @PathVariable int year,
                                   @PathVariable String month,
                                   @PathVariable String city){
        List<Picture> pictures = picturesService.getPitcturesByDate(year, month, city);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("city", city);
        model.addAttribute("pictures", pictures);
        return "pictures";
    }
}
