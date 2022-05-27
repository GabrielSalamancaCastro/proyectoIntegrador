package com.dh.DigitalBooking.Controller;

import com.dh.DigitalBooking.DTO.ImageDTO;
import com.dh.DigitalBooking.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/image")
public class ImageController {

    // ================= ATRIBUTOS ========================//
    private ImageService imageService;
    Logger logger = Logger.getLogger(String.valueOf(ImageController.class));

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // ================= METODO POST ========================//
    @PostMapping("/save")   //COMO IMAGEN NO TIENE ATRIBUTO NAME, SE HACE POR TITLE
    public ResponseEntity save(@RequestBody ImageDTO imageDTO) {
        ResponseEntity response;
        if (imageService.findImageByTitle(imageDTO.getTitle()) != null) {
            response = new ResponseEntity("Image already exist!", HttpStatus.CONFLICT);
        } else if (imageDTO.getTitle().isEmpty() || imageDTO.getUrl().isEmpty()) {
            response = new ResponseEntity("Cannot have empty/void spaces", HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity(imageService.save(imageDTO), HttpStatus.OK);
        }
        return response;
    }

    // ================= METODO GET ========================//
    @GetMapping("/id/{id}")
    public ImageDTO findById(@PathVariable Long id) {
        logger.info("Searching image by ID");
        return imageService.findById(id).orElse(null);
    }

    @GetMapping("/title/{title}")  //PARA IMAGEN USO TITLE YA QUE NO TIENE ATRIBUTO NAME
    public ImageDTO findImageByTitle (@PathVariable String title) {
        logger.info("Searching image by title");
        return imageService.findImageByTitle(title);
    }

    @GetMapping()
    public List<ImageDTO> findAll() {
        logger.info("Image List");
        return imageService.findAll();
    }

    // ================= METODO UPDATE ========================//
    @PutMapping("/update")
    public ResponseEntity updateImage (@RequestBody ImageDTO imageDTOUpdate)  {
        ResponseEntity responseEntity;
        if(imageService.findById(imageDTOUpdate.getId()).isPresent()) {
            responseEntity = new ResponseEntity(imageService.update(imageDTOUpdate), HttpStatus.OK);
        } else {
            logger.info("Image");
            responseEntity = new ResponseEntity("Image not found!", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
        }

    // ================= METODO DELETE ========================//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteImage (@PathVariable Long id) {
        ResponseEntity responseEntity;

        if(imageService.findById(id).isPresent()) {
            imageService.delete(id);
            responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Image has been successfully eliminated!");
        } else {
            responseEntity = new ResponseEntity("Image not found!", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
        }

    // ================= SOME TEST ========================//
    @PostMapping("/test")
    public List<ImageDTO> imageTest(){
        ImageDTO imageDTO1 = new ImageDTO("reanult sandero", "url1");
        ImageDTO imageDTO2 = new ImageDTO("chevrolet sail", "url22");
        ImageDTO imageDTO3 = new ImageDTO("interior renault sandero", "url333");
        ImageDTO imageDTO4 = new ImageDTO("chevrolet sail costado", "url4444");

        ArrayList<ImageDTO> imageDTOArrayList = new ArrayList<>();
        imageDTOArrayList.add(imageService.save(imageDTO1));
        imageDTOArrayList.add(imageService.save(imageDTO2));
        imageDTOArrayList.add(imageService.save(imageDTO3));
        imageDTOArrayList.add(imageService.save(imageDTO4));

        return imageDTOArrayList;
    }


}
