package com.insat.gestionformation.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private String name;
    private String familyName;
    private String mail;
    private String passwd;
    private boolean isAdmin;

    @OneToMany(mappedBy = "host")
    private List<Event> hosted;

    @OneToMany(mappedBy = "participant")
    private List<Participation> participations;

    public List<Event> getHosted() {
        return hosted;
    }

    public void setHosted(List<Event> hosted) {
        this.hosted = hosted;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "FAMILYNAME")
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Column(name = "MAIL")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "PASSWD")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
}
