package com.program.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.submission.TeacherSubmission;
import com.program.model.teacher.TeacherEvent;

import java.util.Date;
import java.util.List;

public class EventDetailResponse {

    private TeacherEvent teacherEvent;

    private List<TeacherSubmission> teacherSubmissionList;

    public EventDetailResponse() {
    }

    @JsonProperty("teacher_id")
    public Long getTeacherId(){
        return teacherEvent.getTeacherId();
    }

    @JsonProperty("event_id")
    public Integer getEventId(){
        return teacherEvent.getEventId();
    }

    @JsonProperty("event_name")
    public String getEventName() {
        return teacherEvent.getEventName();
    }

    @JsonProperty("event_percentage")
    public Integer getEventPercentage(){
        return teacherEvent.getEventPercentage();
    }

    @JsonProperty("event_rate")
    public String getEventRate() {
        return teacherEvent.getEventRate();
    }

    @JsonProperty("approve_name")
    public String getApproveName() {
        return teacherEvent.getApproveName();
    }

    @JsonProperty("submission_status")
    public boolean isSubmissionStatus() {
        return teacherEvent.isSubmissionStatus();
    }

    @JsonProperty("modify_date")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm")
    public Date getModifyDate() {
        return teacherEvent.getModifyDate();
    }

    @JsonProperty("comment")
    public String getComment() {
        return teacherEvent.getComment();
    }

    @JsonProperty("submissions")
    public List<TeacherSubmission> getTeacherSubmissionList() {
        return teacherSubmissionList;
    }

    public void setTeacherEvent(TeacherEvent teacherEvent) {
        this.teacherEvent = teacherEvent;
    }

    public void setTeacherSubmissionList(List<TeacherSubmission> teacherSubmissionList) {
        this.teacherSubmissionList = teacherSubmissionList;
    }


}
