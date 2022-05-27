package com.dh.DigitalBooking.Controller;
import com.dh.DigitalBooking.DTO.ProductDTO;
import com.dh.DigitalBooking.DTO.ReservationDTO;
import com.dh.DigitalBooking.Model.Product;
import com.dh.DigitalBooking.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    // ======= ATRIBUTOS ==========//
    private ProductService productService;
    Logger logger = Logger.getLogger(String.valueOf(ProductController.class));

    // ======= CONSTRUCTOR ==========//
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ===== METODO POST ===== //
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody ProductDTO productDTO) {
        ResponseEntity response;
        if (productService.findProductByName(productDTO.getName()) != null) {
            response = new ResponseEntity("Product already exist!", HttpStatus.CONFLICT);
        } else if (productDTO.getName().isEmpty() || productDTO.getDescription().isEmpty()) {
            response = new ResponseEntity("Cannot have empty/void spaces", HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity(productService.save(productDTO), HttpStatus.OK);
        }
        return response;
    }

    // ===== METODOS GET ===== //
    @GetMapping("/id/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        logger.info("Searching product by ID");
        return productService.findById(id).orElse(null);
    }

    @GetMapping("/name/{name}")  //PARA IMAGEN USO TITLE YA QUE NO TIENE ATRIBUTO NAME
    public ProductDTO findProductByName (@PathVariable String name) {
        logger.info("Searching product by name");
        return productService.findProductByName(name);
    }

    @GetMapping("/category/{category}")
    public List<ProductDTO> findProductByCategory(@PathVariable String category){
        logger.info("Searching product by category");
        return productService.findProductByCategory(category);
    }

    @GetMapping("/city/{city}")
    public List<Product> findProductByCity(@PathVariable String city){
        logger.info("Searching product by city");
        return productService.findProductByCity(city);
    }


    @GetMapping()
    public List<ProductDTO> findAll() {
        logger.info("product List");
        return productService.findAll();
    }

    @GetMapping("/reservation/{id}")
    public List<ReservationDTO> findReservationByProductId(@PathVariable Long id){
        logger.info("Fetching all reservations that the product have");
        return productService.findReservationByProductId(id);
    }

    @GetMapping("/available/startDate={startDate}&&endDate={endDate}")
    public List<Product>listAvailableProductsByDate(@PathVariable Date startDate, @PathVariable Date endDate ){
        logger.info("List of all available Products by dates");
        return productService.listAvailableProductsByDate(startDate, endDate);
    }

    @GetMapping("available/city={city}&&startDate={startDate}&&endDate={endDate}")
    public List<Product> findProductByCityDates(@PathVariable String city, @PathVariable Date startDate, @PathVariable Date endDate){
        List<Product> availableProducts = new ArrayList<>();

        if(city != null && startDate != null && endDate != null){
            List<Product> filterByDate = productService.listAvailableProductsByDate(startDate,endDate);
            for (Product p: filterByDate) {
                if(p.getCity().getName().equalsIgnoreCase(city)){
                    availableProducts.add(p);
                }
            }

        }
        if(city == null && startDate != null && endDate != null){
            logger.info("List of all available Products between: " + startDate + "and" + endDate );
            availableProducts = productService.listAvailableProductsByDate(startDate,endDate);
        }
        if (city !=null && startDate == null && endDate == null ){
            logger.info("List of all available Products in the city: " + city );
            availableProducts= productService.findProductByCity(city);
        }
        return availableProducts;
    }


    // ================= METODO UPDATE ========================//


    // ================= METODO DELETE ========================//



}
