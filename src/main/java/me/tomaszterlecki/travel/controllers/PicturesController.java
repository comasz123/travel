package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Picture;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.IPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PicturesController {
    @Autowired
    IPicturesService picturesService;
    @Autowired
    ICitiesService citiesService;

    @RequestMapping(value="/pictures/{year}/{month}/{id}", method = RequestMethod.GET)
    public String picturesForADate(Model model,
                                   @PathVariable int year,
                                   @PathVariable String month,
                                   @PathVariable int id){
        City city = citiesService.getCityById(id);
        List<Picture> pictures = picturesService.getPitcturesByDateAndCity(year, month, city);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("city", city);
        model.addAttribute("pictures", pictures);
        return "pictures";
    }
}
