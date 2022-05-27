package com.dh.DigitalBooking.Controller;

import com.dh.DigitalBooking.DTO.CharacteristicDTO;
import com.dh.DigitalBooking.Service.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/characteristic")
public class CharacteristicController {

    // ================= ATRIBUTOS ========================//
    private CharacteristicService characteristicService;
    Logger logger = Logger.getLogger(String.valueOf(CharacteristicController.class));

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    // ================= METODO POST ========================//
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CharacteristicDTO characteristicDTO) {
        ResponseEntity response;         // AQUÍ SOLO EVALÚO NAME Y ICON EN ESPACIOS VACÍOS
        if (characteristicService.findCharacteristicByName(characteristicDTO.getName()) != null) {
            response = new ResponseEntity("Characteristic already exist!", HttpStatus.CONFLICT);
        } else if (characteristicDTO.getName().isEmpty() || characteristicDTO.getIcon().isEmpty()) {
            response = new ResponseEntity("Cannot have empty/void spaces", HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity(characteristicService.save(characteristicDTO), HttpStatus.OK);
        }
        return response;
    }

    // ================= METODO GET ========================//
    @GetMapping("/id/{id}")
    public CharacteristicDTO findById(@PathVariable Long id) {
        logger.info("Searching characteristic by ID");
        return characteristicService.findById(id).orElse(null);
    }

    @GetMapping("/name/{name}")
    public CharacteristicDTO findCharacteristicByName(@PathVariable String name) {
        logger.info("Searching characteristic by name");
        return characteristicService.findCharacteristicByName(name);
    }

    @GetMapping()
    public List<CharacteristicDTO> findAll() {
        logger.info("Characteristic List");
        return characteristicService.findAll();
    }

    // ================= METODO UPDATE ========================//
    @PutMapping("/update")
    public ResponseEntity updateCharacteristic (@RequestBody CharacteristicDTO characteristicDTOUpdate)  {
        ResponseEntity responseEntity;
        if(characteristicService.findById(characteristicDTOUpdate.getId()).isPresent()) {
            responseEntity = new ResponseEntity(characteristicService.update(characteristicDTOUpdate), HttpStatus.OK);
        } else {
            logger.info("Characteristic");
            responseEntity = new ResponseEntity("Characteristic not found!", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
        }

    // ================= METODO DELETE ========================//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCharacteristic (@PathVariable Long id) {
        ResponseEntity responseEntity;
        if(characteristicService.findById(id).isPresent()) {
            characteristicService.delete(id);
            responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Characteristic has been successfully eliminated!");
        } else {
            responseEntity = new ResponseEntity("Characteristic not found!", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
        }

    // ================= SOME TEST ========================//


}
