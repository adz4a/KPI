package com.program.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private Integer eventId;

    private String eventName;
    private String eventStatus;
    private String eventCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statusId")
    @JsonIgnore
    private Status status;

    public Event() {
        super();
    }

    public Event(Integer eventId, String eventName, String eventStatus, String eventCategory, Status status) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.eventCategory = eventCategory;
        this.status = status;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventStatus='" + eventStatus + '\'' +
                ", eventCategory='" + eventCategory + '\'' +
                ", status=" + status +
                '}';
    }
}
