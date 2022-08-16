package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.database.IPicturesDAO;
import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.Picture;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.IPicturesService;
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
    @Autowired
    IPicturesService picturesService;
    @Autowired
    IPicturesDAO picturesDAO;
    @Autowired
    IEntitySaver entitySaver;
    @Autowired
    ICitiesService citiesService;

    @RequestMapping(value="/upload", method = RequestMethod.GET)
    public String addPicture(Model model){
        model.addAttribute("picture", new Picture());
        //mamy wszystkie kraje i wszystkie miasta odwiedzone
        List<CitiesForAGivenCountry> countries = citiesService.getCitiesInCountry();
        model.addAttribute("elements", countries);
        return "addpicture";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String addPicture (@ModelAttribute Picture picture, @RequestParam("image")MultipartFile multipartFile){
        picturesDAO.createPartialPicture(picture);


        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        picture.setFileName(fileName);
        String uploadDirectory = "src/main/resources/static/img/";
        FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
        entitySaver.persistEntity(picture);
        return "redirect:/";
    }

}
