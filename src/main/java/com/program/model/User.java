package com.program.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.models.auth.In;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Email(message = "{user.email.not.valid}")
    @NotEmpty(message = "{user.email.not.empty}")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "{user.name.not.empty}")
    private String name;
    @NotEmpty(message = "{user.password.not.empty}")
    @Length(min = 5, message = "{user.password.length}")
    private String password;

    private String categoryName;
    private String statusName;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "statusId")
//    private Status status;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Status> statuses =new ArrayList<>();



    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))

    private List<Event> events;

    @JsonIgnore
    public boolean isAdmin() {
        String roleName = "ADMIN";
        return roles.stream().map(Role::getRole).anyMatch(roleName::equals);
    }

    @JsonIgnore
    public boolean isUser() {
        String roleName = "USER";
        return roles.stream().map(Role::getRole).anyMatch(roleName::equals);
    }

    @JsonIgnore
    public boolean isObserver() {
        String roleName = "OBSERVER";
        return roles.stream().map(Role::getRole).anyMatch(roleName::equals);
    }

//    public List<Event> getUserEvents(String categoryName, String statusName) {
//        if (Objects.equals(this.categoryName, categoryName) && Objects.equals(this.statusName, statusName)){
//            User userEventId =
//        }
//    }


    public User() {
        super();
    }

    @JsonIgnore
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public User(Integer userId, String email, String name, String password, String categoryName, String statusName, List<Role> roles, List<Event> events) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.roles = roles;
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                email.equals(user.email) &&
                name.equals(user.name) &&
                categoryName.equals(user.categoryName) &&
                statusName.equals(user.statusName) &&
                password.equals(user.password) &&
                Objects.equals(events, user.events) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, name, password,categoryName, statusName,events, roles);
    }

    @Override
    public String toString() {
        return "User{" +
//                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", statusName='" + statusName + '\'' +
//                ", roles=" + roles +
                ", events=" + events +
                '}';
    }
}
