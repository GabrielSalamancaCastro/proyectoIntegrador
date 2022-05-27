package com.dh.DigitalBooking.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDTO {

    // ================= ATRIBUTOS ======================== //
    private Long id;
    private String title;
    private String url;


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

    // ================= CONSTRUCTOR======================== //


    public ImageDTO(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public ImageDTO() {
    }


}
