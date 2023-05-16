package com.program.model.role;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.program.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    public Role() {
    }

    public Role(ERole role) {
        this.role = role;
    }

    public Role(ERole role, List<User> users) {
        this.role = role;
        this.users = users;
    }

    @JsonProperty("role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @JsonProperty("role")
    public ERole getName() {
        return role;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

//    @Override
//    public String toString() {
//        return this.role;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(roleId, role1.roleId) &&
                Objects.equals(role, role1.role) &&
                Objects.equals(users, role1.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role, users);
    }
}
