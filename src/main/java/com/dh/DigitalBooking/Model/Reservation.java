package com.dh.DigitalBooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    @JsonIgnoreProperties({"hybernateLazyInitializer", "handler"})
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonIgnoreProperties({"hybernateLazyInitializer", "handler"})
    private User user;

    // AQUÍ ESTÁN LAS DOS CLASES QUE VINCULA: producto(carro) y usuario(cliente que renta el carro)
    // private User user;
    // private VehicleUser vehicleUser;

    private LocalDate startDate;
    private LocalDate endDate;
    private String startHour;


    public Reservation(Product product, User user, LocalDate startDate, LocalDate endDate, String startHour) {
        this.product = product;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startHour = startHour;
    }

    public Reservation() { }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Product getProduct() {        return product;    }

    public void setProduct(Product product) {        this.product = product;    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getEndDate() {        return endDate;    }

    public void setEndDate(LocalDate endDate) {        this.endDate = endDate;    }

    public LocalDate getStartDate() {       return startDate;    }

    public void setStartDate(LocalDate startDate) {        this.startDate = startDate;    }

    public String getStartHour() {        return startHour;    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }



    @Override
    public String toString(){  //COLOCAR LUEGO AQUÍ AL USUARIO(CLIENTE)
        return "Reservation{" +
                "id=" + id +

                ", vehicle=" + product +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startHour=" + startHour +
                '}';
    }
}
