package com.insat.gestionformation.services;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService{

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents(){
        return (List<Event>) eventRepository.findAll();
    }

    public Event getEvent(long id){
        return (Event) eventRepository.findOne(id);
    }

    public Event addEvent(Event event){
        return (Event) eventRepository.save(event);
    }

    public Event updateEvent(Event event){ return (Event) eventRepository.save(event); }

    public void deleteEvent(long id){
        eventRepository.delete(id);
    }
}

