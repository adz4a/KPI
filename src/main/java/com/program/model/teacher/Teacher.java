package com.program.model.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

//    @JsonProperty("events")
//    public List<Event> getEventsByStatus() {
//        if (status!= null){
//        return status.getEvents();
//        }
//        else
//            return null;
//    }

    @JsonProperty("teacher_events")
    public List<TeacherEvent> getTeacherEvents() {
        return teacherEvents;
    }

    public void setTeacherEvents(List<TeacherEvent> teacherEvents) {
        this.teacherEvents = teacherEvents;
    }

    public Teacher() {
        super();
    }

    public Teacher(Long teacherId, User user, String categoryName, String statusName) {
        this.teacherId = teacherId;
        this.user = user;
        this.categoryName = categoryName;
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId)
                && Objects.equals(user, teacher.user)
                && Objects.equals(categoryName, teacher.categoryName)
                && Objects.equals(statusName, teacher.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, user, categoryName, statusName);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", user=" + user +
                ", categoryName='" + categoryName + '\'' +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}