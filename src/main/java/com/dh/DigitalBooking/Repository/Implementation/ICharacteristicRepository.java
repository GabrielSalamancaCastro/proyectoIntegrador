package com.dh.DigitalBooking.Repository.Implementation;

import com.dh.DigitalBooking.Model.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ICharacteristicRepository extends JpaRepository <Characteristic, Long> {

    @Query("SELECT characteristic FROM Characteristic characteristic WHERE characteristic.name = ?1")
    Optional<Characteristic> findCharacteristicByName(String name);
}
