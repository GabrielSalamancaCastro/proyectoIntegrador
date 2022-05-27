package com.dh.DigitalBooking.Controller;

import com.dh.DigitalBooking.DTO.ReservationDTO;
import com.dh.DigitalBooking.Model.Reservation;
import com.dh.DigitalBooking.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/reservation")
public class ReservationController {

    // ======= ATRIBUTOS ==========//
    private ReservationService reservationService;
    Logger logger = Logger.getLogger(String.valueOf(ReservationController.class));

    // ======= CONSTRUCTOR ==========//
    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // ===== METODO POST ===== //
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody ReservationDTO reservationDTO){
        ResponseEntity responseEntity;
        if (reservationDTO.getStartHour().isEmpty() || reservationDTO.getStartDate().equals(null)|| reservationDTO.getEndDate().equals(null) ){
            responseEntity = new ResponseEntity("Reservation information cannot have empty/void spaces", HttpStatus.BAD_REQUEST);
        } else {
            responseEntity = new ResponseEntity(reservationService.save(reservationDTO), HttpStatus.OK);
        }
        return responseEntity;
    }

    // ================= METODO GET ========================//
    @GetMapping("/id/{id}")
    public ReservationDTO findById(@PathVariable Long id) {
        logger.info("Searching reservation by ID");
        return reservationService.findById(id).orElse(null);
    }

    @GetMapping("id/{idProduct}")
    public List<ReservationDTO> findReservationByProductId(Long idProduct){
        logger.info("Searching reservations by product ID");
        return reservationService.findReservationByProductId(idProduct);
    }

    @GetMapping()
    public List<ReservationDTO> findAll(){
        logger.info("Reservations List");
        return reservationService.findAll();
    }

    @GetMapping("/user/{idUser}")
    public List<Reservation> findReservatiosMadeByUser(@PathVariable Long idUser){
        logger.info("Searching reservations made by user with id: " + idUser);
        return reservationService.findReservatiosMadeByUser(idUser);
    }

    // ================= METODO UPDATE ========================//
    @PutMapping("/update")
    public ResponseEntity updateReservation (@RequestBody ReservationDTO reservationDTOUpDate) {
        ResponseEntity responseEntity;
        if (reservationService.findById(reservationDTOUpDate.getId()).isPresent()){
            responseEntity = new ResponseEntity(reservationService.update(reservationDTOUpDate),HttpStatus.OK);
        } else {
            logger.info("Reservation");
            responseEntity = new ResponseEntity("Reservation not found!", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    // ================= METODO DELETE ========================//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservation (@PathVariable Long id){
        ResponseEntity responseEntity;
        if (reservationService.findById(id).isPresent()){
            reservationService.delete(id);
            responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Reservation has been successfully eliminated!");
        } else {
            responseEntity = new ResponseEntity("Reservation not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /*

     */

}
