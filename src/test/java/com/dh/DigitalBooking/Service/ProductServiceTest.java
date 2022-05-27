package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void save() {

        /*
        CityDTO city4 = new CityDTO("Bogota4", "Colombia", "33","33");
        cityService1.save(city4);
        CityDTO cityDTO44 = cityService1.findCityByName(city4.getName()); // HAY QUE CREAR OTRO OBJETO PARA GUARDAR LO DE LA BD
        System.out.println("=======*****================");
        System.out.println(cityService1);
        System.out.println("=======*****================");
        System.out.println(cityDTO44.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        System.out.println("=======*****================");
        // List<CityDTO> citiTest3 = cityService1.findCityByName("Bogota"); GENERA ERROR CASTEAR
        System.out.println("=======*****================");
        System.out.println(cityService1.findCityByName(city4.getName())); // Funciona pero no se puede ejecutar 2 veces
        System.out.println("=======*****================");
        // System.out.println(cityDTO44);
        System.out.println("=======*****================");
         */
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findProductByName() {
    }

    @Test
    void findProductByCategory() {
    }

    @Test
    void findProductByCity() {
    }
}