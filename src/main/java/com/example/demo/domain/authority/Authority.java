package com.example.demo.domain.authority;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    public Authority(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Authority() {
    }

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
}