package com.insat.gestionformation.services;

        import com.insat.gestionformation.models.Participation;
        import com.insat.gestionformation.models.ParticipationId;
        import com.insat.gestionformation.models.User;
        import com.insat.gestionformation.repositories.ParticipationRepository;
        import com.insat.gestionformation.repositories.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    @Autowired
    public ParticipationService (ParticipationRepository participationRepository){
        this.participationRepository= participationRepository;
    }

    public List<Participation> getAllParticipations(){
        return (List<Participation>) participationRepository.findAll();
    }

    public Participation addParticipation(Participation participation){ return (Participation) participationRepository.save(participation); }

    public void deleteParticipation(ParticipationId id){
        participationRepository.delete(id);
    }

}

