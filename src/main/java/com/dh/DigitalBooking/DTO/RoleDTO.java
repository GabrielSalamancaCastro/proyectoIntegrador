package com.dh.DigitalBooking.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO {

    // ========= ATRIBUTOS ==========//
    private Long id;
    private String name;

    public RoleDTO(String name) {
        this.name = name;
    }

    public RoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
