package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.CityDTO;
import com.dh.DigitalBooking.Model.City;
import com.dh.DigitalBooking.Repository.Implementation.ICityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CityService implements IEntityService<CityDTO>{

    // ================= ATRIBUTOS ========================//
    private ICityRepository cityRepository;
    private ObjectMapper mapper;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(CategoryService.class));

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public CityService(ICityRepository cityRepository, ObjectMapper mapper) {
        this.cityRepository = cityRepository;
        this.mapper = mapper;
    }


    @Override
    public CityDTO save(CityDTO cityDTO) {
        cityRepository.save(mapper.convertValue(cityDTO,City.class));
        return cityDTO;
    }

    @Override
    public Optional<CityDTO> findById(Long id) {
        CityDTO cityDTO = null;
        Optional<City> c = cityRepository.findById(id);
        if(c.isPresent()){
            cityDTO = mapper.convertValue(c,CityDTO.class);
        }
        return Optional.ofNullable(cityDTO);
    }

    @Override
    public List<CityDTO> findAll() {
        List<City> cities = cityRepository.findAll();
        List<CityDTO> citiesDTO = new ArrayList<>();
        for (City c: cities) {
            citiesDTO.add(mapper.convertValue(c,CityDTO.class));
        }
        return citiesDTO;
    }

    @Override
    public CityDTO update(CityDTO cityDTO) {
        City city= cityRepository.getById(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setCountry(cityDTO.getCountry());
        cityRepository.save(city);
        logger.info("City with ID: "+ city.getId() + " has been successfully updated");
        return mapper.convertValue(city, CityDTO.class);
    }

    @Override
    public void delete(Long id) {
        if(cityRepository.findById(id).isPresent()){
            cityRepository.deleteById(id);
            logger.info("City has been successfully eliminated");
            System.out.println("Successfully eliminated");
        } else {
            logger.info("ID not found");
            System.out.println("City not found");
        }
    }


    public CityDTO findCityByName(String name){
        logger.info("Searching city by name");
        CityDTO cityDTO = null;
        Optional<City> city = cityRepository.findCityByName(name);
        if (city != null){
            cityDTO = mapper.convertValue(city, CityDTO.class);
        }
        return cityDTO;
    }
}


