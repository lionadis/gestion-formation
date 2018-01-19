package com.insat.gestionformation.controllers;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/add")
    public String addEvent(Event event){
        return "/event/add";
    }

    @GetMapping(value = "/all")
    public String allEvent(Model model){
        model.addAttribute("events", eventService.getAllEvents());
        return "/event/all";
    }

    @PostMapping(value = "/add")
    public String addNewEvent(@Valid Event event){
        eventService.addEvent(event);
        return "redirect:/event/all";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return "redirect:/event/all";
    }

}
