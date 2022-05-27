package com.dh.DigitalBooking.DTO;

import com.dh.DigitalBooking.Model.Reservation;
import com.dh.DigitalBooking.Model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    // ============= ATRIBUTOS =========== //
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Role role;



    // ============= CONSTRUCTOR =========== //


    public UserDTO(String name, String lastName, String email, String password, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO() {
    }

    // ============= GETTERS AND SETTERS =========== //


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
