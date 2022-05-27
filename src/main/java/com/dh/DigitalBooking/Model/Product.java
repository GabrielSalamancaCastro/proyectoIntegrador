package com.dh.DigitalBooking.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "product")
public class Product {

    // ================= ATRIBUTOS ======================== //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    private String name;
    private String description;



    @JoinTable(name = "product_characteristc",

            joinColumns = {@JoinColumn(name = "id_product", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_characteristc", nullable = false)}
    )
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Characteristic> characteristics= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL) // POR QUÉ ESTA RELACIÓN MUCHOSPRODUCTOS-UNA IMAGEN QUEDÓ ASÍ ?
    @JoinColumn(name="id_product")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_city", nullable =false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_category", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;

    //AGREGO EL ATRIBUTO DE TIPO RESERVA --- RELACION UN PRODUCTO(CARRO) PARA MUCHAS RESERVAS
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservations;

    //===============CONSTRUCTOR ============================//
    // HAY QUE AGREGAR reservations al constructor ?
    public Product(String name, String description, List<Characteristic> characteristics, List<Image> images, City city, Category category, List<Reservation> reservations) {
        this.name = name;
        this.description = description;
        this.characteristics = characteristics;
        this.images = images;
        this.city = city;
        this.category = category;
    }

    public Product() {    }

    //===============GETTERS AND SETTERS============================//


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

    // SE AGREGA GETTERS Y SETTERS PARA RESERVATIONS
    public List<Reservation> getReservations() {        return reservations;    }

    public void setReservations(List<Reservation> reservations) {        this.reservations = reservations;    }
}
