package com.dh.DigitalBooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "cities")
public class City {
    // ================= ATRIBUTOS ======================== //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Long id;
    private String name;
    private String country;
    private String longitude;
    private String latitude;

    @OneToMany(mappedBy = "city", orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;

    // ================= COSNTRUCTOR ======================== //


    public City(String name, String country, String longitude, String latitude) {
        this.name = name;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public City() {
    }

    // ================= GETTERS AND SETTERS ======================== //

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Product> getProducts() {
        return products;
    }       //REVISAR SI VAN GETTERS Y SETTERS PARA PRODUCTOS

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
