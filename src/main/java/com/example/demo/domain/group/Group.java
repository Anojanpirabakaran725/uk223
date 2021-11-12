package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;

    public Group(UUID id, String name, String desc, Set<User> users) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.users = users;
    }

    public Group() {
    }

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(
                    name = "groups_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "users_id", referencedColumnName = "id"))
    private Set<User> users;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}