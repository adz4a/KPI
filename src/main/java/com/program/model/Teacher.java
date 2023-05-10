package com.program.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer teacherId;

    @Email(message = "{teacher.email.not.valid}")
    @NotEmpty(message = "{teacher.email.not.empty}")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "{teacher.name.not.empty}")
    private String name;
    @NotEmpty(message = "{teacher.password.not.empty}")
    @Length(min = 5, message = "{teacher.password.length}")
    private String password;

    private String categoryName;
    private String statusName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statusId")
//    @JsonIgnore
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "statusId")
    private List<Event> events;


    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

//    public List<Status> getStatuses() {
//        return statuses;
//    }
//
//    public void setStatuses(List<Status> statuses) {
//        this.statuses = statuses;
//    }

    public Integer getStatus() {
        return status.getStatusId();
    }

    public List<Event> getEventsByStatus() {
        return status.getEvents();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
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

    public Teacher(Integer teacherId, String email, String name, String password, String categoryName, String statusName, Status status) {
        this.teacherId = teacherId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) &&
                Objects.equals(email, teacher.email) &&
                Objects.equals(name, teacher.name) &&
                Objects.equals(password, teacher.password) &&
                Objects.equals(categoryName, teacher.categoryName) &&
                Objects.equals(statusName, teacher.statusName) &&
                Objects.equals(status, teacher.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, email, name, password, categoryName, statusName, status);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", statusName='" + statusName + '\'' +
                ", status=" + status +
                '}';
    }


}
