package com.insat.gestionformation.controllers;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.models.Participation;
import com.insat.gestionformation.models.User;
import com.insat.gestionformation.services.EncryptionService;
import com.insat.gestionformation.services.EventService;
import com.insat.gestionformation.services.ParticipationService;
import com.insat.gestionformation.services.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    boolean usedMail=false;
    @Autowired
    UserService userService;
    @Autowired
    ParticipationService participationService;
    @Autowired
    EventService eventService;
    @Autowired
    EncryptionService encryptionService;
    @GetMapping(value = "/signup")
    public String signUp(User user, Model model, HttpSession session){
        model.addAttribute("usedMail",usedMail);
        return "signup";
    }
    @PostMapping(value = "/signup")
    public String addNewUser(@Valid User user){
        User user1=userService.getUserByMail(user.getMail());
        if(user1==null){
            usedMail=false;
            user.setPasswd(encryptionService.encrypt(user.getPasswd()));
            user.setAdmin(false);
            userService.addUser(user);
            return "redirect:/";
        }else{
            usedMail=true;
            return "redirect:/signup";
        }


    }

    @PostMapping(value = "/signin")
    public String connect(@Valid User user, HttpSession session){
        User res=userService.getUserByMail(user.getMail());
        if (res!=null && encryptionService.encrypt(user.getPasswd()).equals(res.getPasswd())){
            session.setAttribute("connected", true);
            session.setAttribute("user", res);
        }else IndexController.setError(true);
        if (session.getAttribute("currentPage")==null) {
            return "redirect:/";
        }
        return "redirect:"+session.getAttribute("currentPage");
    }

    @GetMapping(value = "/disconnect")
    public String signout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping (value="administration")
    public String adminPanel(HttpSession session, Model model){
        User user=(User)session.getAttribute("user");
        boolean admin=false;
        if (user!=null && user.isAdmin()) admin=true;
        model.addAttribute("admin",admin);
        model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping(value = "/user/deleteAdmin/{id}")
    public String deleteEventAdmin(@PathVariable Long id, HttpSession session) {
        User user =(User) session.getAttribute("user");
        if (user!=null && user.isAdmin()){
            userService.deleteUser(id);
        }
        return "redirect:/administration";
    }
}
