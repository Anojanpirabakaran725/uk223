package com.example.demo.domain.appUser;

import com.example.demo.domain.group.Group;
import com.example.demo.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String email;
    private String password;

    public User(UUID id, String username, String email, String password, Set<Role> roles, Group group) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.group = group;
    }

    public User() {
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(
                    name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "groups_id", referencedColumnName = "id"))
    private Group group;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}