package com.insat.gestionformation.controllers;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.models.User;
import com.insat.gestionformation.services.EventService;
import com.insat.gestionformation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = "/event")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping(value = "/add")
    public String addEvent(Event event) {
        return "/event/add";
    }

    @GetMapping(value = "/all")
    public String allEvent(Model model, HttpSession session) {
        model.addAttribute("events", eventService.getAllEvents());
        return "/event/all";
    }

    @PostMapping(value = "/add")
    public String addNewEvent(@Valid Event event, HttpSession session) {
        User host = userService.getUserByMail((String) session.getAttribute("mail"));
        event.setHost(host);
        eventService.addEvent(event);
        return "redirect:/event/all";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/event/all";
    }

    @GetMapping(value = "/details/{id}")
    public String detailsEvent(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.getEvent(id));
        return "/event/details";
    }

    @PostMapping(value = "/details/{id}")
    public String updateEvent(@PathVariable Long id, Model model) {
        Event event = eventService.getEvent(id);
        eventService.deleteEvent(id);
        Set<User> users = event.getParticipants();
        User user = new User();
        user.setName("Nadhem");
        user.setFamilyName("Maaloul");
        users.add(user);
        event.setParticipants(users);
        eventService.addEvent(event);
        model.addAttribute("event", event);
        model.addAttribute("participants", users);
        return "/event/details";
    }

}
