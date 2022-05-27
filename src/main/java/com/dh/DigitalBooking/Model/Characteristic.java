package com.dh.DigitalBooking.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "characteristic")
public class Characteristic {

    // ================= ATRIBUTOS ======================== //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_characteristic")
    private Long id;

    private String name;
    private String icon;

    @ManyToMany(mappedBy = "characteristics")
    @JsonIgnore
    private List<Product> products;

    // ================= CONSTRUCTOR ========================//
    public Characteristic(String name, String icon, List<Product> products) {
        this.name = name;
        this.icon = icon;
        this.products = new ArrayList<>();
    }

    public Characteristic(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public Characteristic() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
