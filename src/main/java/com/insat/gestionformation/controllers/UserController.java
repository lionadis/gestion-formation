package com.insat.gestionformation.controllers;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.models.Participation;
import com.insat.gestionformation.models.User;
import com.insat.gestionformation.services.EncryptionService;
import com.insat.gestionformation.services.EventService;
import com.insat.gestionformation.services.ParticipationService;
import com.insat.gestionformation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ParticipationService participationService;
    @Autowired
    EncryptionService encryptionService;
    @GetMapping(value = "/signup")
    public String signUp(User user){
        return "signup";
    }
    @PostMapping(value = "/signup")
    public String addNewUser(@Valid User user){
        user.setPasswd(encryptionService.encrypt(user.getPasswd()));
        userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "/signin")
    public String connect(@Valid User user, HttpSession session){
        User res=userService.getUserByMail(user.getMail());

        if (res!=null && encryptionService.encrypt(user.getPasswd()).equals(res.getPasswd())){
            session.setAttribute("connected", true);
            session.setAttribute("user", res);
        }
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
}
