package com.dh.DigitalBooking.Repository.Implementation;

import com.dh.DigitalBooking.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    /*
    @Query("SELECT r FROM Reservation r WHERE r.id = ?1") *====* ESTE ME DEVOLVERÍA UN OBJETO RESERVATION
    Optional<Reservation> findReservationById(Long id);  *===*  NO ES NECESARIO HACERLO,,, LO HACE EL JPA
    */


    @Query("SELECT r FROM Reservation r WHERE r.product.id = ?1") // AGARRA EL PRIMER PARÁMETRO
    List<Reservation> findReservationByProductId(Long idProduct);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = ?1")
    List<Reservation> findReservatiosMadeByUser(Long idUser);

}
