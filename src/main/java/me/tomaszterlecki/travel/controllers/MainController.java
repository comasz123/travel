package me.tomaszterlecki.travel.controllers;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @Autowired
    SessionFactory sessionFactory;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String main () {
        return "index_eng";
    }

}
