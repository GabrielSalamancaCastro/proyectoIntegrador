package com.dh.DigitalBooking.Repository.Implementation;

import com.dh.DigitalBooking.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ICategoryRepository extends JpaRepository <Category,Long> {

    @Query("SELECT c FROM Category c WHERE c.name = ?1")
    Optional<Category> findCategoryByName(String name);

}
