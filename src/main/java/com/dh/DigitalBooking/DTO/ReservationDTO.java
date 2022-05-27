package com.dh.DigitalBooking.DTO;

import com.dh.DigitalBooking.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDTO {

    // ================= ATRIBUTOS ======================== //
    private Long id;
    private Product product;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startHour;

    // ================= CONSTRUCTOR ======================== //
    public ReservationDTO(Product product, User user, LocalDate startDate, LocalDate endDate, String startHour) {
        this.product = product;
        this.user= user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startHour = startHour;
    }

    public ReservationDTO() {    }

    // ================= GETTERS AND SETTERS ======================== //

    public Long getId() {       return id;    }

    public Product getProduct() {        return product;    }

    public void setProduct(Product product) {        this.product = product;    }

    public LocalDate getStartDate() {        return startDate;    }

    public void setStartDate(LocalDate startDate) {        this.startDate = startDate;    }

    public LocalDate getEndDate() {        return endDate;    }

    public void setEndDate(LocalDate endDate) {        this.endDate = endDate;    }

    public String getStartHour() {        return startHour;    }

    public void setStartHour(String startHour) {        this.startHour = startHour;    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
