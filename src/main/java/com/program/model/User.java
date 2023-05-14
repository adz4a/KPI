package com.program.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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



//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "statusId")
//    private Status status;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Status> statuses =new ArrayList<>();


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private List<Role> roles;



//    @JsonIgnore
    public boolean isAdmin() {
        String roleName = "Admin";
        return roles.stream().map(Role::getRole).anyMatch(roleName::equals);
    }

//    @JsonIgnore
    public boolean isTeacher() {
        String roleName = "Teacher";
        return roles.stream().map(Role::getRole).anyMatch(roleName::equals);
    }

    @JsonIgnore
    public boolean isObserver() {
        String roleName = "Observer";
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


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public User(Integer userId, String email, String name, String password, List<Role> roles, List<Event> events) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                email.equals(user.email) &&
                name.equals(user.name) &&
                password.equals(user.password) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, name, password, roles);
    }

    @Override
    public String toString() {
        return "User{" +
//                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
//                ", roles=" + roles +
                '}';
    }
}
