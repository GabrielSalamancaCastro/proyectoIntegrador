package com.dh.DigitalBooking.Controller;


import com.dh.DigitalBooking.DTO.RoleDTO;
import com.dh.DigitalBooking.Service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;
    Logger logger = Logger.getLogger(String.valueOf(RoleController.class));

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody RoleDTO roleDTO){
        ResponseEntity response;
        if(roleService.findRoleByName(roleDTO.getName()) != null){
            response = new ResponseEntity("There is an Role already created with this name!", HttpStatus.CONFLICT);
        }else if (roleDTO.getName().isEmpty()){
            response = new ResponseEntity("Cannot have empty/void spaces", HttpStatus.BAD_REQUEST);
        }else{
            response = new ResponseEntity(roleService.save(roleDTO), HttpStatus.CREATED);
        }
        return response;
    }

    @GetMapping()
    public List<RoleDTO> findAll(){
        logger.info("Fetching all roles");
        return roleService.findAll();
    }

    @GetMapping("/id/{id}")
    public RoleDTO findById(@PathVariable Long id){
        logger.info("Seaching Role by id");
        return roleService.findById(id).orElse(null);
    }







}
