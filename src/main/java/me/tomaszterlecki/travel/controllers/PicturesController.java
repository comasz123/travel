package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.database.Picture;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.services.ICitiesService;
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
public class PicturesController {
    @Autowired
    IPicturesService picturesService;
    @Autowired
    ICitiesService citiesService;
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    SessionObject sessionObject;

    @RequestMapping(value="/pictures/{year}/{month}/{id}", method = RequestMethod.GET)
    public String picturesForADate(Model model,
                                   @PathVariable int year,
                                   @PathVariable String month,
                                   @PathVariable int id){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }        City city = citiesService.getCityById(id);
        List<Picture> pictures = picturesService.getPicturesByDateAndCity(year, month, city);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("city", city);
        model.addAttribute("pictures", pictures);
        authenticationService.addCommonInfoToModel(model);
        return "pictures";
    }
}
