package com.insat.gestionformation.controllers;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.models.User;
import com.insat.gestionformation.services.EventService;
import com.insat.gestionformation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping(value = "/signup")
    public String signUp(User user){
        return "signup";
    }
    @PostMapping(value = "/signup")
    public String addNewUser(@Valid User user){
        userService.addUser(user);
        return "redirect:/event/all";
    }

    @GetMapping(value = "/signin")
    public String signIn(User user){
        return "signin";
    }
    @PostMapping(value = "/signin")
    public String connect(@Valid User user){
        //userService.addUser(user);
        return "redirect:/event/all";
    }
}
