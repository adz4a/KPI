package com.program.model.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.Event;
import com.program.model.submission.Submission;
import io.swagger.v3.oas.annotations.Hidden;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeacherEvent {

    @EmbeddedId
    private TeacherEventId id;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

//    @OneToOne
//    @JoinColumn(name = "submission_id")
//    private Submission submission;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
//    private List<Submission> events=new ArrayList<>();

    private boolean isApprove;
    private boolean submissionStatus;

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @JsonProperty("event_name")
    public String getEventName() {
        return event.getEventName();
    }

    @JsonProperty("event_percentage")
    public Integer getEventPercentage(){
        return event.getEventPercentage();
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @JsonProperty("is_approve")
    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    @JsonProperty("submission_status")
    public boolean isSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(boolean submissionStatus) {
        this.submissionStatus = submissionStatus;
    }
}
