package com.program.model.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.User;
import com.program.model.submission.TeacherSubmission;
import javax.persistence.*;
import java.util.List;


@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    private String categoryName;
    private String statusName;

    @OneToMany(mappedBy = "teacher")
    private List<TeacherEvent> teacherEvents;

    @OneToMany(mappedBy = "teacher")
    private List<TeacherSubmission> teacherSubmissions;

    @JsonProperty("teacher_id")
    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @JsonProperty("category_name")
    public String getCategoryName() {
        if (this.categoryName!=null){
        return categoryName;
        }else
            return null;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @JsonProperty("status_name")
    public String getStatusName() {
        if (this.statusName!=null){
            return statusName;
        }else
            return null;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @JsonProperty("name")
    public String getUserName() {
        return user.getName();
    }

    @JsonProperty("email")
    public String getUserEmail() {
        return user.getEmail();
    }

    @JsonIgnore
    public Long getUserIdTeacher() {
        return user.getUserId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("teacher_events")
    public List<TeacherEvent> getTeacherEvents() {
        return teacherEvents;
    }

    public void setTeacherEvents(List<TeacherEvent> teacherEvents) {
        this.teacherEvents = teacherEvents;
    }

    @JsonProperty("teacher_submissions")
    public List<TeacherSubmission> getTeacherSubmissions() {
        return teacherSubmissions;
    }

    public void setTeacherSubmissions(List<TeacherSubmission> teacherSubmissions) {
        this.teacherSubmissions = teacherSubmissions;
    }

    public Teacher() {
        super();
    }


}
