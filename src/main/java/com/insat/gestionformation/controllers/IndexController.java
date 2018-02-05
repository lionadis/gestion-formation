package com.insat.gestionformation.controllers;

import com.insat.gestionformation.models.User;
import com.insat.gestionformation.services.EventService;
import com.insat.gestionformation.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    EventService eventService;
    @Autowired
    ParticipationService participationService;
    @RequestMapping("/")
    public String index(Model model, User user, HttpSession session){
        model.addAttribute("mail", session.getAttribute("mail"));
        model.addAttribute("connected",session.getAttribute("connected"));
        model.addAttribute("usr", session.getAttribute("user"));
        model.addAttribute("events", eventService.getAllEvents());
        session.setAttribute("currentPage","/");
        return "index";
    }
}
