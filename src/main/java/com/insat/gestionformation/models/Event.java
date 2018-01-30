package com.insat.gestionformation.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIME;

@Entity
@Table(name = "EVENT")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private String name;
    private String description;
    private int capacity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern = "HH:mm")
    private Date time;

    @ManyToOne
    @JoinColumn(name="host")
    private User host;

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    @ManyToMany
    @JoinTable(name="participation",
        joinColumns={@JoinColumn(name="id_user")},
        inverseJoinColumns={@JoinColumn(name="id_event")})
    private Set<User> participants;

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    @Column(name = "TIME")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "CAPACITY")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
