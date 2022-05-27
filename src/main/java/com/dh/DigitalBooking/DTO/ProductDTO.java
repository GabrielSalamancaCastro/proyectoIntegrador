package com.dh.DigitalBooking.DTO;

import com.dh.DigitalBooking.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    // ================= ATRIBUTOS ======================== //
    private Long id;

    private String name;
    private String description;
    private List<Characteristic> characteristics;
    private List<Image> images;
    private City city;
    private Category category;


    // ================= CONSTRUCTOR ======================== //


    public ProductDTO(String name, String description, List<Characteristic> characteristics, List<Image> images, City city, Category category) {
        this.name = name;
        this.description = description;
        this.characteristics = new ArrayList<>();
        this.images = new ArrayList<>();
        this.city = city;
        this.category = category;
    }

    public ProductDTO() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
