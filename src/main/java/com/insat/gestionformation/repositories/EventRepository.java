package com.insat.gestionformation.repositories;

import com.insat.gestionformation.models.Event;
import com.insat.gestionformation.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> getEventsByHost(User usr);
}
