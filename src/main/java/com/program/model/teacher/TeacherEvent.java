package com.program.model.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.Event;
import com.program.model.Status;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "submissionId")
    @JsonIgnore
    private Submission submission;





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

    @JsonProperty("submission_id")
    public String getSubmissionId(){
        if (submission!=null){
        return submission.getSubmissionId();
        }else
            return null;
    }
}
