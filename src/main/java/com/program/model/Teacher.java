package com.program.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Teacher() {
        super();
    }

    public Teacher(Integer teacherId, String email, String name, String password, String categoryName, String statusName, List<Event> events) {
        this.teacherId = teacherId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.events = events;
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
                ", events=" + events +
                '}';
    }
}
