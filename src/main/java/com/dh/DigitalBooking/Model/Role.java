package com.dh.DigitalBooking.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    //=== ATRIBUTOS ===//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;
    private String name;


    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;



    //=== CONSTRUCTOR ===//

    public Role(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public Role() {
    }

    //=== GETTERS AND SETTERS ===//
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
