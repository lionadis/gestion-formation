package com.insat.gestionformation.services;

import com.insat.gestionformation.models.User;
import com.insat.gestionformation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){return (List<User>) userRepository.findAll();}

    public User getUser(long id){
        return (User) userRepository.findOne(id);
    }

    public User addUser(User user){ return (User) userRepository.save(user); }

    public void deleteUser(long id){
        userRepository.delete(id);
    }

    public User getUserByMail(String mail){return userRepository.getUserByMail(mail);}
}

