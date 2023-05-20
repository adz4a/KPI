package com.program.model.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.Category;
import com.program.model.Event;
import com.program.model.Status;
import com.program.model.approve.Approve;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "approveId")
    @JsonIgnore
    private Approve approve;

    private boolean submissionStatus;

    @JsonIgnore
//    @JsonProperty("teacher_id")
    public Long getTeacherId(){
        return teacher.getTeacherId();
    }

    @JsonProperty("teacher_name")
    public String getTeacherName() {
        return teacher.getUserName();
    }

    @JsonProperty("teacher_status")
    public String getTeacherStatus(){
        return teacher.getStatusName();
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @JsonProperty("event_id")
    public Integer getEventId(){
        return event.getEventId();
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

    @JsonProperty("approve_name")
    public String getApproveName() {
        return approve.getApproveName();
    }

    public Approve getApprove() {
        return approve;
    }

    public void setApprove(Approve approve) {
        this.approve = approve;
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

    public void setSubmissionId(String submissionId){
        this.submission.setSubmissionId(submissionId);
    }



}
