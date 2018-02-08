package com.insat.gestionformation.services;

import com.insat.gestionformation.models.Participation;
import com.insat.gestionformation.models.User;
import com.insat.gestionformation.repositories.EventRepository;
import com.insat.gestionformation.repositories.ParticipationRepository;
import com.insat.gestionformation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insat.gestionformation.models.Event;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;
    EventRepository eventRepository;

    @Autowired
    public UserService(UserRepository userRepository, ParticipationRepository participationRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.participationRepository= participationRepository;
        this.eventRepository=eventRepository;
    }

    public List<User> getAllUsers(){return (List<User>) userRepository.findAll();}

    public User getUser(long id){
        return (User) userRepository.findOne(id);
    }

    public User addUser(User user){ return (User) userRepository.save(user); }

    public void deleteUser(long id){
        User e =userRepository.findOne(id);
        List<Event> le= eventRepository.getEventsByHost(e);
        for (Event ev:le) {
            eventRepository.delete(ev.getId());
            List<Participation> psss= participationRepository.getParticipationsByEvent(ev);
            for (Participation pss:psss) participationRepository.delete(pss.getParticipationId());
        }
        List<Participation> ps= participationRepository.getParticipationsByParticipant(e);
        for (Participation p:ps) participationRepository.delete(p.getParticipationId());
        userRepository.delete(id);
    }

    public User getUserByMail(String mail){return userRepository.getUserByMail(mail);}
}
