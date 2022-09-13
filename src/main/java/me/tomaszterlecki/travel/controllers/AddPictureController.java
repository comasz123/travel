package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.database.IEntitySaver;

import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.database.Country;
import me.tomaszterlecki.travel.model.database.Picture;
import me.tomaszterlecki.travel.services.*;
import me.tomaszterlecki.travel.session.SessionObject;
import me.tomaszterlecki.travel.utilities.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AddPictureController {
//
    @Autowired
    IEntitySaver entitySaver;
    @Autowired
    ICitiesService citiesService;
    @Autowired
    SessionObject sessionObject;
    @Autowired
    ISessionService sessionService;
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    ICountriesService countriesService;
    @Autowired
    IMonthService monthService;
    @Autowired
    IPicturesService picturesService;

    @RequestMapping(value="/add/picture", method = RequestMethod.GET)
    public String addPicture(Model model){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        authenticationService.addCommonInfoToModel(model);

        model.addAttribute("picture", new Picture());
        model.addAttribute("elements", citiesService.getAllCitiesInAllCountries());
        model.addAttribute("months", monthService.getAllMonthsInEnglish());
        return "/addpicture/addpicture";
    }
    @RequestMapping(value = "/add/picture", method = RequestMethod.POST)
    public String addPicture (@ModelAttribute("picture") Picture picture,
                              @RequestParam("image")MultipartFile multipartFile,
                              @RequestParam("cityId") int cityId){

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        picture.setFileName(fileName);
        String uploadDirectory = "src/main/resources/static/img/";
        FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
        picture.setCity(citiesService.getCityById(cityId));
        picture.setUser(sessionObject.getUser());

        entitySaver.persistEntity(picture);
        sessionService.setCitiesCountriesAndYears();
//        redirect idzie na controller
        return "redirect:/add/picture";
    }

    @RequestMapping(value = "add/picture/country", method = RequestMethod.GET)
    public String addCountry(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        model.addAttribute("country", new Country());
        return "addpicture/add-country";
    }

    @RequestMapping(value = "add/picture/country", method = RequestMethod.POST)
    public String addCountry(@ModelAttribute("country") Country country) {

        entitySaver.persistEntity(country);
        return "redirect:/add/picture";
    }

    @RequestMapping(value = "add/picture/city", method = RequestMethod.GET)
    public String addCity(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        model.addAttribute("city", new City());
        model.addAttribute("countries", countriesService.getAllCountries());
        return "addpicture/add-city";
    }

    @RequestMapping(value = "add/picture/city", method = RequestMethod.POST)
    public String addCity(@ModelAttribute("city") City city) {
        entitySaver.persistEntity(city);
        return "redirect:/add/picture";
    }
    @RequestMapping(value = "add/picture/city/country", method = RequestMethod.GET)
    public String addCountryToCity(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        model.addAttribute("country", new Country());
        return "addpicture/add-country";
    }

    @RequestMapping(value = "add/picture/city/country", method = RequestMethod.POST)
    public String addCountryToCity(@ModelAttribute("country") Country country) {

        entitySaver.persistEntity(country);
        return "redirect:/add/picture/city";
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String testing(Model model){
        model.addAttribute("countries", this.countriesService.getAllCountries());
        model.addAttribute("elements", this.citiesService.getAllCitiesInAllCountries());
        model.addAttribute("cities", this.citiesService.getAllCities());
        model.addAttribute("city", new City());

        return "test/test4";
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestParam("cityId") int id, Model model){
        City city = citiesService.getCityById(id);
        System.out.println(city.getId());
        System.out.println(city.getNameEng());
        model.addAttribute("city", city);
        return "test/show-test";
    }

}
