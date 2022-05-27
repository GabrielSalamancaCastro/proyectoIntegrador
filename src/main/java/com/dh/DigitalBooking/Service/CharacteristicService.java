package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.CategoryDTO;
import com.dh.DigitalBooking.DTO.CharacteristicDTO;
import com.dh.DigitalBooking.Model.Category;
import com.dh.DigitalBooking.Model.Characteristic;
import com.dh.DigitalBooking.Repository.Implementation.ICharacteristicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CharacteristicService implements IEntityService<CharacteristicDTO>{

    // ================= ATRIBUTOS ========================//
    private ICharacteristicRepository characteristicRepository;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(CategoryService.class));
    private ObjectMapper mapper;

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public CharacteristicService(ICharacteristicRepository characteristicRepository, ObjectMapper mapper) {
        this.characteristicRepository = characteristicRepository;
        this.mapper = mapper;
    }

    // ================= MÉTODOS CRUD ========================//
    @Override
    public CharacteristicDTO save(CharacteristicDTO characteristicDTO) {
        characteristicRepository.save(mapper.convertValue(characteristicDTO, Characteristic.class));
        return characteristicDTO;
    }

    @Override
    public Optional<CharacteristicDTO> findById(Long id) {
        CharacteristicDTO characteristicDTO = null;
        Optional<Characteristic> charact = characteristicRepository.findById(id);
        if (charact.isPresent()){
            characteristicDTO = mapper.convertValue(charact, CharacteristicDTO.class);
        }
        return Optional.ofNullable(characteristicDTO);
    }

    @Override
    public List<CharacteristicDTO> findAll() {
        List<Characteristic> characteristics = characteristicRepository.findAll();
        List<CharacteristicDTO> characteristicDTO = new ArrayList<>();  // Revisar si hay que usar Set en lugar de ArrayList
        for (Characteristic eachCharacteristic: characteristics) {
            characteristicDTO.add(mapper.convertValue(eachCharacteristic, CharacteristicDTO.class));
        }
        return characteristicDTO;
    }

    @Override
    public CharacteristicDTO update(CharacteristicDTO characteristicDTO) {
        Characteristic charact = characteristicRepository.getById(characteristicDTO.getId());
        charact.setIcon(characteristicDTO.getIcon());
        charact.setName(characteristicDTO.getName());
        characteristicRepository.save(charact);
        logger.info("City with ID: "+ charact.getId() + " has been successfully updated");
        return mapper.convertValue(charact, CharacteristicDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (characteristicRepository.findById(id).isPresent()){
            characteristicRepository.deleteById(id);
            logger.info("Characteristic has been successfully eliminated");
            System.out.println("Successfully eliminated");
        } else {
            logger.info("ID not found");
            System.out.println("Characteristic not found");
        }
    }

    public CharacteristicDTO findCharacteristicByName(String name){
        logger.info("Searching characteristic by name");
        CharacteristicDTO characteristicDTO = null;
        Optional<Characteristic> charact = characteristicRepository.findCharacteristicByName(name);
        if(charact != null) {
            characteristicDTO = mapper.convertValue(charact, CharacteristicDTO.class);
        }
        return characteristicDTO;
    }

}
