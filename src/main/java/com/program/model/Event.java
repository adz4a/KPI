package com.program.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.teacher.TeacherEvent;

import javax.persistence.*;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    private String eventName;
    private Integer eventPercentage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statusId")
    @JsonIgnore
    private Status status;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<TeacherEvent> teacherEvents;

    public Event() {
        super();
    }

    @JsonProperty("event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @JsonProperty("event_name")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @JsonProperty("event_percentage")
    public Integer getEventPercentage() {
        return eventPercentage;
    }

    public void setEventPercentage(Integer eventPercentage) {
        this.eventPercentage = eventPercentage;
    }

    public void setTeacherEvents(List<TeacherEvent> teacherEvents) {
        this.teacherEvents = teacherEvents;
    }

    public Status getStatus() {
        return status;
    }

    public Status setStatus(Status status) {
        this.status = status;
        return status;
    }

    @JsonProperty("category_name")
    public String getCategoryName(){
        return status.getCategoryName();
    }

    @JsonProperty("status_name")
    public String getStatusName(){
        return status.getStatusName();
    }

    @JsonIgnore
    public Integer getStatusId(){
        return status.getStatusId();
    }

}
