package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.UserDTO;
import com.dh.DigitalBooking.Model.Role;
import com.dh.DigitalBooking.Model.User;
import com.dh.DigitalBooking.Repository.Implementation.IRoleRepository;
import com.dh.DigitalBooking.Repository.Implementation.IUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService implements IEntityService<UserDTO>, UserDetailsService {

    // ================= ATRIBUTOS ========================//
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(ImageService.class));

    // ================= CONSTRUCTOR ========================//

    @Autowired
    public UserService(IUserRepository userRepository, IRoleRepository roleRepository, ObjectMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(mapper.convertValue(userDTO, User.class));
        return userDTO;
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        UserDTO userDTO = null;
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userDTO = mapper.convertValue(user,UserDTO.class);
        }
        return Optional.ofNullable(userDTO);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User u: users) {
            userDTOS.add(mapper.convertValue(u,UserDTO.class));
        }
        return userDTOS;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userRepository.getById(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        logger.info("User with ID: "+ user.getId() + " has been successfully updated");
        return mapper.convertValue(user,UserDTO.class);
    }

    @Override
    public void delete(Long id) {

        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            logger.info("User has been successfully eliminated");
            System.out.println("Successfully eliminated");
        } else{
            logger.info("User not found");
            System.out.println("User not found");
        }
    }

    public UserDTO findUserByEmail(String email){
        logger.info("Searching user by email");
        UserDTO userDTO = null;
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user != null) {
            userDTO = mapper.convertValue(user, UserDTO.class);
        }
        return userDTO;
    }

    public void addRolToUser(String email, String roleName){
        User user = userRepository.findUserByEmail(email).orElse(null);
        Role role = roleRepository.findRoleByName(roleName).orElse(null);
        user.setRole(role);
    }

    // ====== SEGURIDAD ========
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException("User has not been found in the database");
        }else{
            logger.info("user found in the database");
        }
        Collection<SimpleGrantedAuthority> autorities = new ArrayList<>();
        autorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),autorities);
    }
}
