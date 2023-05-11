package com.program.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer teacherId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    private String categoryName;
    private String statusName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId")
    private Status status;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status.getStatusId();
    }

    public String getUserName() {
        return user.getName();
    }

    public String getUserEmail() {
        return user.getEmail();
    }

    @JsonIgnore
    public Integer getUserIdTeacher() {
        return user.getUserId();
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @JsonIgnore
    public List<Event> getEventsByStatus() {
        return status.getEvents();
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    //    public List<Event> getEvents() {
//        List<Event> matchingEvents = new ArrayList<>();
//        Integer status = getStatus();
//        for (Event event : events) {
//            if (event.getStatusId().equals(status)) {
//                matchingEvents.add(event);
//            }
//        }
//        return matchingEvents;
//    }
//

    public Teacher() {
        super();
    }

    public Teacher(Integer teacherId, User user, String categoryName, String statusName, Status status) {
        this.teacherId = teacherId;
        this.user = user;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId)
                && Objects.equals(user, teacher.user)
                && Objects.equals(categoryName, teacher.categoryName)
                && Objects.equals(statusName, teacher.statusName)
                && Objects.equals(status, teacher.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, user, categoryName, statusName, status);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", user=" + user +
                ", categoryName='" + categoryName + '\'' +
                ", statusName='" + statusName + '\'' +
                ", status=" + status +
                '}';
    }
}
