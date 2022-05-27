package com.dh.DigitalBooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    // ================= ATRIBUTOS ========================//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;

    private String name;
    private String description;
    private String imgUrl;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    // ================= CONSTRUCTOR ========================//

    public Category() { }

    public Category(String name, String description, String imgUrl) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
