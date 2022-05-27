package com.dh.DigitalBooking.Service;

import com.dh.DigitalBooking.DTO.CategoryDTO;
import com.dh.DigitalBooking.DTO.ImageDTO;
import com.dh.DigitalBooking.Model.Category;
import com.dh.DigitalBooking.Model.Image;
import com.dh.DigitalBooking.Repository.Implementation.IImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ImageService implements IEntityService<ImageDTO>{

    // ================= ATRIBUTOS ========================//
    private IImageRepository imageRepository;
    private ObjectMapper mapper;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(ImageService.class));

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public ImageService(IImageRepository imageRepository, ObjectMapper mapper) {
        this.imageRepository = imageRepository;
        this.mapper = mapper;
    }

    // ================= MÉTODOS CRUD ========================//
    @Override
    public ImageDTO save(ImageDTO imageDTO) {
        imageRepository.save(mapper.convertValue(imageDTO, Image.class));
        return imageDTO;
    }

    @Override
    public Optional<ImageDTO> findById(Long id) {
        ImageDTO imageDTO = null;
        Optional<Image> img = imageRepository.findById(id);
        if (img.isPresent()){
            imageDTO = mapper.convertValue(img, ImageDTO.class);
        }

        return Optional.ofNullable(imageDTO);
    }

    @Override
    public List<ImageDTO> findAll() {
        List<Image> images = imageRepository.findAll();
        List<ImageDTO> imageDTOS = new ArrayList<>(); //revisar si es mejor un Set que el ArrayList
        for (Image eachImage: images) {
            imageDTOS.add(mapper.convertValue(eachImage, ImageDTO.class));
        }
        return imageDTOS;
    }

    @Override
    public ImageDTO update(ImageDTO imageDTO) {
        Image image = imageRepository.getById(imageDTO.getId());
        image.setTitle(imageDTO.getTitle());
        image.setUrl(imageDTO.getUrl());
        imageRepository.save(image);
        logger.info("Image with ID: "+ image.getId() + " has been successfully updated");
        return mapper.convertValue(image, ImageDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (imageRepository.findById(id).isPresent()){
            imageRepository.deleteById(id);
            logger.info("Image has been successfully eliminated");
            System.out.println("Successfully eliminated");
        } else {
            logger.info("ID not found");
            System.out.println("City not found");
        }
    }

    public ImageDTO findImageByTitle(String title) {
        logger.info("Searching image by name");
        ImageDTO imgDTO = null;
        Optional<Image> image = imageRepository.findImageByTitle(title);
        if(image != null) {
            imgDTO = mapper.convertValue(image, ImageDTO.class);
        }
        return imgDTO;
    }
}
