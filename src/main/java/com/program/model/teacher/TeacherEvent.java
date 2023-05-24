package com.program.model.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.Event;
import com.program.model.approve.Approve;
import com.program.model.role.ERole;
import com.program.model.role.Role;

import javax.persistence.*;
import java.util.Objects;


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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "approveId")
    @JsonIgnore
    private Approve approve;

    private boolean submissionStatus;

    private String comment;


    @JsonIgnore
//    @JsonProperty("teacher_id")
    public Long getTeacherId(){
        return teacher.getTeacherId();
    }

    @JsonProperty("teacher_name")
    public String getTeacherName() {
        return teacher.getUserName();
    }

    @JsonProperty("department_name")
    public String getTeacherDepartment(){
        return teacher.getDepartmentName();
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

    @JsonIgnore
    public boolean isAccept() {
        String approveName = "Accepted";
        return Objects.equals(approve.getApproveName(), approveName);
    }

    @JsonIgnore
    public boolean isReject() {
        String approveName = "Rejected";
        return Objects.equals(approve.getApproveName(), approveName);
    }

    @JsonIgnore
    public boolean isNone() {
        String approveName = "none";
        return Objects.equals(approve.getApproveName(), approveName);
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

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSubmissionStatus(boolean submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

}
