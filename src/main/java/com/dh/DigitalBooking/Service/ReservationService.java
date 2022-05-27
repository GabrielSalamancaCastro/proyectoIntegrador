package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.ReservationDTO;
import com.dh.DigitalBooking.Model.Reservation;
import com.dh.DigitalBooking.Repository.Implementation.IReservationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ReservationService implements IEntityService<ReservationDTO>{

    // ================= ATRIBUTOS ========================//
    private IReservationRepository reservationRepository;
    private ObjectMapper mapper;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(ReservationService.class));

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public ReservationService(IReservationRepository reservationRepository, ObjectMapper mapper) {
        this.reservationRepository = reservationRepository;
        this.mapper = mapper;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        reservationRepository.save(mapper.convertValue(reservationDTO, Reservation.class));
        return reservationDTO;
    }

    @Override
    public Optional<ReservationDTO> findById(Long id) {
        ReservationDTO reservationDTO = null;
        Optional <Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()){
            reservationDTO = mapper.convertValue(reservation, ReservationDTO.class);
        }
        return Optional.ofNullable(reservationDTO);
    }

    @Override
    public List<ReservationDTO> findAll() {
        List <Reservation> reservations = reservationRepository.findAll();
        List <ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation:reservations) {
            reservationDTOS.add(mapper.convertValue(reservation, ReservationDTO.class));
        }
        return reservationDTOS;
    }

    @Override
    public ReservationDTO update(ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.getById(reservationDTO.getId());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStartDate(reservation.getStartDate());
        reservation.setStartHour(reservation.getStartHour());
        reservation.setProduct(reservation.getProduct()); //SE PUEDE CAMBIAR EL TIPO DE CARRO ALQUILADO
        reservationRepository.save(reservation);
        logger.info("Reservation with ID: " + reservation.getId() + "has been successfully updated");
        return mapper.convertValue(reservation, ReservationDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (reservationRepository.findById(id).isPresent()){
            reservationRepository.deleteById(id);
            logger.info("Reservation has been successfully eliminated");
            System.out.println("Succesfully eliminated");
        } else {
            logger.info("ID not found");
            System.out.println("Reservation not found");
        }
    }

    public List<ReservationDTO> findReservationByProductId(Long idProduct){
        logger.info("Searching reservations by Product ID");
        List<ReservationDTO> reservationDTO = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.findReservationByProductId(idProduct);
        for (Reservation reservation:reservations){
            reservationDTO.add(mapper.convertValue(reservation, ReservationDTO.class));
        }
        return reservationDTO;
    }


    public  List<Reservation> findReservatiosMadeByUser(Long idUser){
        logger.info("Searching reservations made by user with id: " + idUser);
        return reservationRepository.findReservatiosMadeByUser(idUser);
    }

    /*
    public List<ProductDTO> findProductByCategory(String category){
    logger.info("Searching products by category");
        List<ProductDTO> p = new ArrayList<>();
        List<Product> products = productRepository.findProductByCategory(category);

        for (Product pro: products) {
            p.add(mapper.convertValue(pro, ProductDTO.class));
        }
        return p;
     */
}
