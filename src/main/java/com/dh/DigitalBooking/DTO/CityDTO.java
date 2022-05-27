package com.dh.DigitalBooking.DTO;

import com.dh.DigitalBooking.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDTO {

    // ================= ATRIBUTOS ======================== //
    private Long id;
    private String name;
    private String country;
    private String longitude;
    private String latitude;
    private List<Product> products;


    // ================= CONSTRUCTOR ======================== //


    public CityDTO(String name, String country, String longitude, String latitude) {
        this.name = name;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public CityDTO(String name, String country, String longitude, String latitude, List<Product> products) {
        this.name = name;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.products = new ArrayList<>();
    }

    public CityDTO() {    }

    // ================= GETTER AND SETTERS ======================== //


    public Long getId() {        return id;    }

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
    }

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
