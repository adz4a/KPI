package com.program.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.program.model.audit.DateAudit;
import com.program.model.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

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


    @JsonIgnore
    public boolean isAdmin() {
        String roleName = "Admin";
        return roles.stream().map(Role::getName).anyMatch(roleName::equals);
    }

    @JsonIgnore
    public boolean isTeacher() {
        String roleName = "Teacher";
        return roles.stream().map(Role::getName).anyMatch(roleName::equals);
    }

    @JsonIgnore
    public boolean isObserver() {
        String roleName = "Observer";
        return roles.stream().map(Role::getName).anyMatch(roleName::equals);
    }

//    public List<Event> getUserEvents(String categoryName, String statusName) {
//        if (Objects.equals(this.categoryName, categoryName) && Objects.equals(this.statusName, statusName)){
//            User userEventId =
//        }
//    }

    @JsonIgnore
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

//    @JsonIgnore
    public List<Role> getRoles() {
        return roles == null ? null : new ArrayList<>(roles);
    }

    public void setRoles(List<Role> roles) {
        if (roles == null) {
            this.roles = null;
        } else {
            this.roles = Collections.unmodifiableList(roles);
        }
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
