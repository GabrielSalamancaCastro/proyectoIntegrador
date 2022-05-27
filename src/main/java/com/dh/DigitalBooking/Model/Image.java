package com.dh.DigitalBooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name= "images")
public class Image {
    // ================= ATRIBUTOS ======================== //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long id;

    private String title;
    private String url;

    // POR QUÉ NO APARECE RELACIÓN MUCHAS IMÁGENES - UN PRODUCTO AQUÍ ?


    // ================= CONSTRUCTOR ======================== //




    public Image(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public Image() {
    }

    // ================= GETTERS AND SETTERS ======================== //
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
