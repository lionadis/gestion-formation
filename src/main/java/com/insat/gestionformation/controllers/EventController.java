package com.insat.gestionformation.controllers;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.models.Participation;
import com.insat.gestionformation.models.ParticipationId;
import com.insat.gestionformation.models.User;
import com.insat.gestionformation.services.EventService;
import com.insat.gestionformation.services.ParticipationService;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping(value = "/event")
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final ParticipationService participationService;

    @Autowired
    public EventController(EventService eventService, UserService userService, ParticipationService participationService) {
        this.eventService = eventService;
        this.userService = userService;
        this.participationService =participationService;
    }

    @GetMapping(value = "/add")
    public String addEvent(Event event) {
        return "/event/add";
    }

    @GetMapping(value = "/participate/{id_event}")
    public String participate(HttpSession session, @PathVariable Long id_event){
        Event event =eventService.getEvent(id_event);
        User user=(User)session.getAttribute("user");
        Participation participation=new Participation(user,event,new ParticipationId(user.getId(),event.getId()));
        participationService.addParticipation(participation);
        return "redirect:/event/details/"+id_event;

    }
    @PostMapping(value = "/add")
    public String addNewEvent(@Valid Event event, HttpSession session) {
        User host = (User)session.getAttribute("user");
        event.setHost(host);
        eventService.addEvent(event);
        return "redirect:/";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String detailsEvent(@PathVariable Long id, Model model,User user, HttpSession session) {
        Event event=eventService.getEvent(id);
        boolean connected=false;
        if(session.getAttribute("connected")!=null&& (boolean)session.getAttribute("connected")) connected=true;

        boolean isPart=false;
        boolean isHost=false;
        model.addAttribute("connected",connected);
        if (connected) {
            User usr =(User)session.getAttribute("user");
            model.addAttribute("usr",usr);
            for (Participation p:event.getParticipants()) {
                if (p.getParticipationId().getParticipant()==usr.getId() && p.getParticipationId().getParticipated()==event.getId()){
                    isPart=true;
                    break;
                }
            }
            if ((event.getHost().equals(userService.getUserByMail(usr.getMail())))) {
                isPart=true;
                isHost=true;
                ArrayList<User> participants=new ArrayList<User>();
                for (Participation p:event.getParticipants()){
                    participants.add(p.getParticipant());
                }
                model.addAttribute("participants",participants);
                model.addAttribute("isHost",isHost);
            }

        }
        model.addAttribute("event", event);
        model.addAttribute("isPart", isPart);
        session.setAttribute("currentPage","/event/details/"+id);
        return "/event/details";
    }

}
