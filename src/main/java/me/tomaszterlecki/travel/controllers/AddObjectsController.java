package me.tomaszterlecki.travel.controllers;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.model.database.Country;
import me.tomaszterlecki.travel.services.ISessionService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="admin")
public class AddObjectsController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    IEntitySaver entitySaver;

    @RequestMapping(value = "/country/add", method = RequestMethod.GET)
    public String addCountryAdmin(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "index";
        }
        model.addAttribute("country", new Country());
        return "addobjects/add-country";
    }

    @RequestMapping(value = "/country/add", method = RequestMethod.POST)
    public String addCountryAdmin(@ModelAttribute("country") Country country) {

        entitySaver.persistEntity(country);
        return "redirect:/edit/countries";
    }
}
