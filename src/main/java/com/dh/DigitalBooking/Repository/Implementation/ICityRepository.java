package com.dh.DigitalBooking.Repository.Implementation;

import com.dh.DigitalBooking.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
@Transactional
public interface ICityRepository extends JpaRepository <City,Long> {

    @Query("SELECT city FROM City city WHERE city.name = ?1")
    Optional<City> findCityByName(String name);
}
