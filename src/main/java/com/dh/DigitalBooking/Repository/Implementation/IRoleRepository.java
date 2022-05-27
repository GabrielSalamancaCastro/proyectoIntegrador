package com.dh.DigitalBooking.Repository.Implementation;

import com.dh.DigitalBooking.Model.Role;
import com.dh.DigitalBooking.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IRoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Optional<Role> findRoleByName(String name);

}
