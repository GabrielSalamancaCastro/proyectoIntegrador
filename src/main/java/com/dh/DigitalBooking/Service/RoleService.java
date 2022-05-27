package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.RoleDTO;
import com.dh.DigitalBooking.Model.Role;
import com.dh.DigitalBooking.Repository.Implementation.IRoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IEntityService<RoleDTO>{

    // ================= ATRIBUTOS ========================//
    private IRoleRepository roleRepository;
    private ObjectMapper mapper;
    Logger logger = Logger.getLogger(String.valueOf(ReservationService.class));

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public RoleService(IRoleRepository roleRepository, ObjectMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }



    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        roleRepository.save(mapper.convertValue(roleDTO, Role.class));
        logger.info("Role has been successfully saved");
        return roleDTO;
    }

    @Override
    public Optional<RoleDTO> findById(Long id) {
        logger.info("Search Role by ID");
        RoleDTO roleDTO = null;
        Optional<Role> r = roleRepository.findById(id);
        if(r.isPresent()) {
            roleDTO = mapper.convertValue(r, RoleDTO.class);
        }
        return Optional.ofNullable(roleDTO);
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> rolesDTO = new ArrayList<>();
        for(Role r : roles) {
            rolesDTO.add(mapper.convertValue(r, RoleDTO.class));
        }
        logger.info("Searching all roles");
        return rolesDTO;
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        Role role = roleRepository.findById(roleDTO.getId()).get();
        role.setName(roleDTO.getName());
        logger.info("Role with ID: "+ role.getId() + " has been successfully updated");
        roleRepository.save(role);
        return mapper.convertValue(role, RoleDTO.class);
    }

    @Override
    public void delete(Long id) {
        if(roleRepository.findById(id).isPresent()){
            roleRepository.deleteById(id);
            logger.info("Role deleted correctly!");
            System.out.println("Role deleted correctly!");
        } else {
            logger.error("Role not found!");
            System.out.println("Role not found!");
        }
    }

    public RoleDTO findRoleByName(String name){
        logger.info("Searching Role by name");
        RoleDTO roleDTO = null;
        Optional<Role> role = roleRepository.findRoleByName(name);
        if(role != null){
            roleDTO = mapper.convertValue(role,RoleDTO.class);
        }
        return roleDTO;

    }


}
