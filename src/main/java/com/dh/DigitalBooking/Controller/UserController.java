package com.dh.DigitalBooking.Controller;

import com.dh.DigitalBooking.DTO.ProductDTO;
import com.dh.DigitalBooking.DTO.UserDTO;
import com.dh.DigitalBooking.Service.ProductService;
import com.dh.DigitalBooking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.logging.Logger;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    // ======= ATRIBUTOS ==========//
    private UserService userService;
    Logger logger = Logger.getLogger(String.valueOf(ImageController.class));

    // ======= CONSTRUCTOR ==========//
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ===== METODO POST ===== //
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody UserDTO userDTO) {
        ResponseEntity response;
        if (userService.findUserByEmail(userDTO.getEmail()) != null) {
            response = new ResponseEntity("There is an User already registered with this email!", HttpStatus.CONFLICT);
        } else if (userDTO.getName().isEmpty() || userDTO.getLastName().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            response = new ResponseEntity("Cannot have empty/void spaces", HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity(userService.save(userDTO), HttpStatus.CREATED);
        }
        return response;
    }

    // ===== METODO GET ===== //

    @GetMapping("")
    public List<UserDTO> findAll(){
        logger.info("Users list");
        return userService.findAll();
    }

    @GetMapping("/id/{id}")
    public UserDTO findById(@PathVariable Long id){
        logger.info("Searching user by id");
        return userService.findById(id).orElse(null);
    }

    @GetMapping("email/{email}")
    public UserDTO findByEmail(@PathVariable String email){
        logger.info("Searching user by email");
        return userService.findUserByEmail(email);
    }




}


