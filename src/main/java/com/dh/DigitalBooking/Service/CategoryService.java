package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.CategoryDTO;
import com.dh.DigitalBooking.DTO.ReservationDTO;
import com.dh.DigitalBooking.Model.Category;
import com.dh.DigitalBooking.Model.Reservation;
import com.dh.DigitalBooking.Repository.Implementation.ICategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CategoryService implements IEntityService<CategoryDTO>{

    // ================= ATRIBUTOS ========================//
    private ICategoryRepository categoryRepository;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(CategoryService.class));
    private ObjectMapper mapper;



    // ================= CONSTRUCTOR ========================//
    @Autowired
    public CategoryService(ICategoryRepository categoryRepository, ObjectMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }



    // ================= METODO POST ========================//
    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        categoryRepository.save(mapper.convertValue(categoryDTO, Category.class));
        return categoryDTO;
    }

    // ================= METODOS GET ========================//
    @Override
    public Optional<CategoryDTO> findById(Long id) {
        CategoryDTO cDTO = null;
        Optional<Category> c = categoryRepository.findById(id);
        if(c.isPresent()){
            cDTO = mapper.convertValue(c,CategoryDTO.class);
        }
        return Optional.ofNullable(cDTO);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTO = new ArrayList<>();
        for(Category cat : categories) {
            categoryDTO.add(mapper.convertValue(cat, CategoryDTO.class));
        }
        return categoryDTO;
    }



    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        Category cat = categoryRepository.findById(categoryDTO.getId()).get();
        cat.setName(categoryDTO.getName());
        cat.setDescription(categoryDTO.getDescription());
        cat.setImgUrl(categoryDTO.getImgUrl());
        categoryRepository.save(cat);
        logger.info("Category with ID: "+ cat.getId() + " has been successfully updated");
        return mapper.convertValue(cat, CategoryDTO.class);
    }

    @Override
    public void delete(Long id) {
        if(categoryRepository.findById(id).isPresent()){
            categoryRepository.deleteById(id);
            logger.info("Category has been successfully eliminated");
            System.out.println("Successfully eliminated");
        } else {
            logger.info("ID not found");
            System.out.println("Category not found");
        }
    }


    public CategoryDTO findCategoryByName(String name) {
        logger.info("Searching category by name");
        CategoryDTO cDTO = null;
        Optional<Category> c = categoryRepository.findCategoryByName(name);
        if(c != null) {
            cDTO = mapper.convertValue(c, CategoryDTO.class);
        }
        return cDTO;
    }


}
