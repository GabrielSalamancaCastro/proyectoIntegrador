package com.dh.DigitalBooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "carUser")
public class User {

    // ================= ATRIBUTOS ========================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_role")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Role role;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservations;

    // ================= CONSTRUCTOR ========================//


    public User(String name, String lastName, String email, String password, Role role, List<Reservation> reservations) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    // ================= GETTERS AND SETTERS ========================//


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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
