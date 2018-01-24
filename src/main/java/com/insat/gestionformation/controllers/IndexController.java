package com.insat.gestionformation.controllers;

import com.insat.gestionformation.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    EventService eventService;
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("events", eventService.getAllEvents());
        return "index";
    }
}
