package com.insat.gestionformation.repositories;

import com.insat.gestionformation.models.Participation;
import com.insat.gestionformation.models.ParticipationId;
import com.insat.gestionformation.models.User;
import javafx.util.Pair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.insat.gestionformation.models.Event;

import java.util.List;

@Repository
public interface ParticipationRepository extends CrudRepository<Participation, ParticipationId> {
    List<Participation> getParticipationsByEvent(Event event);
    List<Participation> getParticipationsByParticipant(User user);


}
