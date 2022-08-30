package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
import me.tomaszterlecki.travel.model.Picture;
import me.tomaszterlecki.travel.services.IAuthenticationService;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.ICountriesService;
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

import java.util.List;

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
    IAuthenticationService authenticationService;
    @Autowired
    ICountriesService countriesService;

    @RequestMapping(value="/upload", method = RequestMethod.GET)
    public String addPicture(Model model){
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        model.addAttribute("picture", new Picture());
        //mamy wszystkie kraje i wszystkie miasta odwiedzone
        List<CitiesForAGivenCountry> allCountriesAndCities = citiesService.getAllCitiesInAllCountries();
        model.addAttribute("elements", allCountriesAndCities);
        authenticationService.addCommonInfoToModel(model);
        return "addpicture";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String addPicture (@ModelAttribute Picture picture, @RequestParam("image")MultipartFile multipartFile){
//        picturesDAO.createPartialPicture(picture);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        picture.setFileName(fileName);
        String uploadDirectory = "src/main/resources/static/img/";
        FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
        entitySaver.persistEntity(picture);
        return "redirect:/upload";
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String testing(Model model){
        model.addAttribute("countries", this.countriesService.getAllCountries());
        model.addAttribute("elements", this.citiesService.getAllCitiesInAllCountries());
        model.addAttribute("testname", "ZULU");

        return "test4";
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestParam("countries") int countryId, @RequestParam("cities") String cityNameEng, Model model){
        Country country = countriesService.getCountryByID(countryId);

        model.addAttribute("country", country);
        return "show-test";
    }

}
