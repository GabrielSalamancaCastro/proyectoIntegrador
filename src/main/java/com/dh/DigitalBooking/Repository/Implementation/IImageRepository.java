package com.dh.DigitalBooking.Repository.Implementation;

import com.dh.DigitalBooking.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT img FROM Image img WHERE img.title = ?1")
    Optional<Image> findImageByTitle(String title);
}
