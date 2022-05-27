package com.dh.DigitalBooking.Controller;

import com.dh.DigitalBooking.DTO.CategoryDTO;
import com.dh.DigitalBooking.DTO.CityDTO;
import com.dh.DigitalBooking.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/city")
public class CityController {

    // ================= ATRIBUTOS ========================//
    private CityService cityService;
    Logger logger = Logger.getLogger(String.valueOf(CityController.class));

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // ================= METODO POST ========================//
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CityDTO cityDTO) { //AQUÍ SE VERIFICA SI LA CIUDAD EXISTE
        ResponseEntity responseEntity;                         //O SI HAY ESPACIOS VACÍOS ANTES DE GRABARLA
        if (cityService.findCityByName(cityDTO.getName()) != null){
            responseEntity = new ResponseEntity("City already exists!", HttpStatus.CONFLICT);
        } else if (cityDTO.getName().isEmpty() || cityDTO.getCountry().isEmpty() ) {
            responseEntity = new ResponseEntity("Cannot have empty/void spaces", HttpStatus.BAD_REQUEST);
        } else {
            responseEntity = new ResponseEntity(cityService.save(cityDTO), HttpStatus.OK);
        }
        return responseEntity;
    }

    // ================= METODO GET ========================//
    @GetMapping("/id/{id}")
    public CityDTO findById(@PathVariable Long id){
        logger.info("Searching category by ID");
        return cityService.findById(id).orElse(null);
    }

    @GetMapping("/name/{name}")
    public CityDTO findCityByName(@PathVariable String name){
        logger.info("Searching city by name");
        return cityService.findCityByName(name);
    }

    //FORMALMENTE NO SE PIDE UN METODO DE BÚSQUEDA DE CIUDADES POR PAÍS
    // SI LO IMPLEMENTAMOS, DEBERÍA DEVOLVER UN ARRAY CON LAS CIUDADES DEL PAÍS

    @GetMapping()
    public List<CityDTO> findAll() {
        logger.info("City List");
        return cityService.findAll();
    }

    // ================= METODO UPDATE ========================//
    @PutMapping ("/update")
    public ResponseEntity updateCity(@RequestBody CityDTO cityDTOUpadate) {
        ResponseEntity responseEntity;
        if (cityService.findById(cityDTOUpadate.getId()).isPresent()){
            responseEntity = new ResponseEntity(cityService.update(cityDTOUpadate), HttpStatus.OK);
        } else {
            logger.info("City");
            responseEntity = new ResponseEntity("City not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    // ================= METODO DELETE ========================//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCity(@PathVariable Long id){  //SI SE PUEDE EN EL SPRINT 4 REALIZAR UNA CONFIRMACIÓN
        ResponseEntity responseEntity;                        //PREVIA ANTES DE BORRAR

        if (cityService.findById(id).isPresent()) {  // VALIDA PRIMERO SI EXISTE EL ID PARA PODER BORRAR
            cityService.delete(id);
            responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body("City has been succesfully deleted!");
        } else {
            responseEntity = new ResponseEntity("City not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    // ================= SOME TEST ========================//


}
