package com.insat.gestionformation.services;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.models.Participation;
import com.insat.gestionformation.repositories.EventRepository;
import com.insat.gestionformation.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService{

    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    @Autowired
    public EventService(EventRepository eventRepository, ParticipationRepository participationRepository) {
        this.eventRepository = eventRepository;
        this.participationRepository= participationRepository;
    }

    public List<Event> getAllEvents(){
        return (List<Event>) eventRepository.findAll();
    }

    public Event getEvent(long id){
        Event e=(Event) eventRepository.findOne(id);
        return e;
    }

    public Event addEvent(Event event){
        return (Event) eventRepository.save(event);
    }

    public Event updateEvent(Event event){ return (Event) eventRepository.save(event); }

    public void deleteEvent(long id){
        Event e =eventRepository.findOne(id);
        List<Participation> ps= participationRepository.getParticipationsByEvent(e);
        for (Participation p:ps) participationRepository.delete(p.getParticipationId());
        eventRepository.delete(id);

    }


}

