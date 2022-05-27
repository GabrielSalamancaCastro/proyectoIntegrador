package com.dh.DigitalBooking.DTO;

import com.dh.DigitalBooking.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacteristicDTO {

    // ================= ATRIBUTOS ======================== //

    private Long id;
    private String name;
    private String icon;
    private List<Product> products;

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

// ================= CONSTRUCTOR ======================== //


    public CharacteristicDTO(String name, String icon, List<Product> products) {
        this.name = name;
        this.icon = icon;
        this.products = new ArrayList<>();
    }

    public CharacteristicDTO() {
    }

    public CharacteristicDTO(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }
}
