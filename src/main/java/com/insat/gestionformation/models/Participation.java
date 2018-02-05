package com.insat.gestionformation.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Participation")
public class Participation implements Serializable {

    @AttributeOverrides({
            @AttributeOverride(
                    name = "id_user",
                    column = @Column(name = "id_user")),
            @AttributeOverride(
                    name = "id_event",
                    column = @Column(name = "id_event"))
    })

    @EmbeddedId
    private ParticipationId participationId;


    @ManyToOne
    @JoinColumn(name = "id_user", insertable=false, updatable=false)
    private User participant;
    @ManyToOne
    @JoinColumn(name = "id_event" ,insertable=false ,updatable=false)
    private Event event;

    public Participation(){}
    public Participation(User user, Event event, ParticipationId pid){
        this.participant=user;
        this.event=event;
        this.participationId=pid;
    }
    public ParticipationId getParticipationId() {
        return participationId;
    }

    public void setParticipationId(ParticipationId participationId) {
        this.participationId = participationId;
    }

    public User getParticipant() {
        return participant;
    }

    public void setParticipant(User participant) {
        participant = participant;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
