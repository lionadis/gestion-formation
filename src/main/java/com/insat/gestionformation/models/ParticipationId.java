package com.insat.gestionformation.models;


import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ParticipationId implements Serializable{

    @Column(name="id_user")
    private Long participant;
    @Column(name = "id_event")
    private Long participated;


    public ParticipationId(Long participant, Long participated){
        this.participant=participant;
        this.participated=participated;
    }

    public ParticipationId(){}
    public Long getParticipant() {
        return participant;
    }

    public void setParticipant(Long participant) {
        this.participant = participant;
    }

    public Long getParticipated() {
        return participated;
    }

    public void setParticipated(Long participated) {
        this.participated = participated;
    }
}
