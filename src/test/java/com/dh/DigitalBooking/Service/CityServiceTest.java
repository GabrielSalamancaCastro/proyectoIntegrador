package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.CityDTO;
import com.dh.DigitalBooking.Model.City;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
@SpringBootTest
class CityServiceTest {

    @Autowired
    CityService cityService1;

    @Test
    void findCityByName() {
        CityDTO cityDTO123 = new CityDTO("Seattle123", "USA", "50", "40");
        cityService1.save(cityDTO123);
        // boolean result1 = cityService1.findCityByName("New York").getName().isEmpty();
        // assertFalse(result1);
        CityDTO testCity1 = cityService1.findCityByName("Seattle123");
        assertEquals("Seattle123", testCity1.getName());
    }

    @Test
    void delete() {
        CityDTO city2 = new CityDTO("Cali", "Colombia", "22","22");
        cityService1.save(city2); // PRIMERO HAY QUE GUARDARLO
        CityDTO cityDTO3 = cityService1.findCityByName(city2.getName()); // HAY QUE CREAR OTRO OBJETO PARA GUARDAR LO DE LA BD
        System.out.println("=======*****================");
        System.out.println(city2.getCountry());
        System.out.println(city2.getName());
        System.out.println(city2.getLatitude());
        System.out.println(city2.getLongitude());
        System.out.println(city2.getProducts());
        System.out.println(city2.getId());
        System.out.println(cityDTO3.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        System.out.println("=======*****================");
        cityService1.delete(cityDTO3.getId()); // APLICAMOS EL MÉTODO QUE CORRESPONDA
        // cityService1.delete(5L);
        Assert.assertTrue(cityService1.findById(cityDTO3.getId()).isEmpty()); // APLICAMOS EL TEST QUE CORRESPONDA
    }

    @Test
    void save() {
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
    }

    @Test
    void findById() {
        CityDTO city100 = new CityDTO("Buenos Aires", "Argentina", "100","100");
        cityService1.save(city100);
        CityDTO cityDTO100 = cityService1.findCityByName(city100.getName()); // HAY QUE CREAR OTRO OBJETO PARA GUARDAR LO DE LA BD
        System.out.println("=======*****================");
        System.out.println(cityDTO100.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        System.out.println("latitud es: " + city100.getLatitude()+ " y longitud es :" + city100.getLongitude());
        System.out.println("=======*****================");
        // System.out.println(cityService1.findById(1L));
        System.out.println(cityService1.findById(cityDTO100.getId()));
        Assert.assertTrue(cityService1.findById(cityDTO100.getId()).isPresent()); // APLICAR EL TEST QUE CORRESPONDA
        System.out.println("=======*****================");
    }

    @Test
    void findAll() {
        CityDTO city200 = new CityDTO("Buenos Aires", "Argentina", "200","200");
        CityDTO city300 = new CityDTO("Rosario", "Argentina", "300","300");
        CityDTO city400 = new CityDTO("Mar del Plata", "Argentina", "400","400");
        cityService1.save(city200);
        cityService1.save(city300);
        cityService1.save(city400);
        CityDTO cityDTO200 = cityService1.findCityByName(city200.getName()); // HAY QUE CREAR OTRO OBJETO PARA GUARDAR LO DE LA BD
        CityDTO cityDTO300 = cityService1.findCityByName(city300.getName()); // HAY QUE CREAR OTRO OBJETO PARA GUARDAR LO DE LA BD
        CityDTO cityDTO400 = cityService1.findCityByName(city400.getName()); // HAY QUE CREAR OTRO OBJETO PARA GUARDAR LO DE LA BD
        List <CityDTO> cityDTOList = new ArrayList<>();
        cityDTOList.add(cityDTO200);
        cityDTOList.add(cityDTO300);
        cityDTOList.add(cityDTO400);
        System.out.println("=======*****================");
        System.out.println("una ciudad es: " + city200.getName() + ", una latitud es: " + city300.getLatitude()+ " y un país es :" + city400.getCountry());
        System.out.println("=======*****================");
        System.out.println(cityDTO200.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        System.out.println(cityDTO300.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        System.out.println(cityDTO400.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        cityService1.findAll();
        System.out.println("=======*****================");
        System.out.println(cityService1.findAll());
        System.out.println("=======*****================");
        System.out.println(cityDTOList);
    }

    @Test
    void update() {
        CityDTO city5000 = new CityDTO("Buenos Aires 5000", "Argentina", "5000","5000");
        CityDTO city6000 = new CityDTO("Rosario 6000", "Argentina", "6000","6000");
        CityDTO city7000 = new CityDTO("Mar del Plata 7000", "Argentina", "7000","7000");
        cityService1.save(city5000);
        cityService1.save(city6000);
        cityService1.save(city7000);
        CityDTO cityDTO5000 = cityService1.findCityByName(city5000.getName()); // CREAMOS OTRO OBJETO PARA GUARDAR LO DE LA BD
        CityDTO cityDTO6000 = cityService1.findCityByName(city6000.getName()); // CREAMOS OTRO OBJETO PARA GUARDAR LO DE LA BD
        CityDTO cityDTO7000 = cityService1.findCityByName(city7000.getName()); // CREAMOS OTRO OBJETO PARA GUARDAR LO DE LA BD
        System.out.println("=======*****================");
        System.out.println("una ciudad es: " + city5000.getName() + ", una latitud es: " + city6000.getLatitude()+ " y un país es :" + city7000.getCountry());
        System.out.println("=======*****================");
        System.out.println(cityDTO5000.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        System.out.println(cityDTO6000.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        System.out.println(cityDTO7000.getId()); //HAY QUE PREGUNTAR EL NÚMERO DEL ID AL OBJETO RECIÉN CREADO
        System.out.println(cityService1.findCityByName(cityDTO5000.getName()));
        System.out.println(cityService1.findCityByName("Rosario 6000"));
        System.out.println(cityService1.findCityByName(cityDTO7000.getName()));
        System.out.println("=======*****================");
        // city5000.setCountry("Uruguay");
        cityDTO5000.setName("Montevideo");
        cityService1.update(cityDTO5000);
        // cityService1.update(cityDTO5000);
        // CityDTO cityDTO5000Dos = cityService1.findCityByName(cityDTO5000.getName());
        // System.out.println(cityDTO5000Dos.getId());
    }

}

