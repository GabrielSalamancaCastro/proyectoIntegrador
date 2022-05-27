package com.dh.DigitalBooking.Repository.Implementation;

import com.dh.DigitalBooking.Model.Image;
import com.dh.DigitalBooking.Model.Product;
import com.dh.DigitalBooking.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Optional<Product> findProductByName(String name);

    @Query("SELECT p FROM Product p WHERE p.category.name = ?1")
    List<Product> findProductByCategory(String category);

    @Query("SELECT p FROM Product p WHERE p.city.name = ?1")
    List<Product> findProductByCity(String city);

    @Query("SELECT r FROM Reservation r WHERE r.product.id =?1")
    List<Reservation> findReservationByProductId(Long id);

    @Query(value = "SELECT * FROM product INNER JOIN reservations ON reservations.id_product = product.id_product " +
            "WHERE reservations.end_date>=?1 AND reservations.start_date<=?2", nativeQuery = true)
    List<Product> listNotAvailableProductsByDate(Date startDate, Date endDate);


}
