package com.dh.DigitalBooking.Service;


import com.dh.DigitalBooking.DTO.ProductDTO;
import com.dh.DigitalBooking.DTO.ReservationDTO;
import com.dh.DigitalBooking.Model.Product;
import com.dh.DigitalBooking.Model.Reservation;
import com.dh.DigitalBooking.Repository.Implementation.IProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductService implements IEntityService<ProductDTO>{

    // ================= ATRIBUTOS ========================//
    private IProductRepository productRepository;
    private ObjectMapper mapper;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(ImageService.class));

    // ================= CONSTRUCTOR ========================//
    @Autowired
    public ProductService(IProductRepository productRepository, ObjectMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    @Override
    public ProductDTO save(ProductDTO productDTO) {
        productRepository.save(mapper.convertValue(productDTO, Product.class));
        return productDTO;
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        ProductDTO productDTO = null;
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productDTO = mapper.convertValue(product, ProductDTO.class);
        }
        return Optional.ofNullable(productDTO);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>(); //revisar si es mejor un Set que el ArrayList
        for (Product product: products) {
            productDTOS.add(mapper.convertValue(product, ProductDTO.class));
        }
        return productDTOS;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        Product product = productRepository.getById(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        productRepository.save(product);
        logger.info("Product with ID: "+ product.getId() + " has been successfully updated");
        return mapper.convertValue(product, ProductDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            logger.info("Product has been successfully eliminated");
            System.out.println("Successfully eliminated");
        } else {
            logger.info("ID not found");
            System.out.println("Product not found");
        }
    }

    public ProductDTO findProductByName(String name){
        logger.info("Searching product by name");
        ProductDTO productDTO = null;
        Optional<Product> product = productRepository.findProductByName(name);
        if(product != null) {
            productDTO = mapper.convertValue(product, ProductDTO.class);
        }
        return productDTO;
    }

    public List<ProductDTO> findProductByCategory(String category){
        logger.info("Searching products by category");
        List<ProductDTO> p = new ArrayList<>();
        List<Product> products = productRepository.findProductByCategory(category);

        for (Product pro: products) {
            p.add(mapper.convertValue(pro, ProductDTO.class));
        }
        return p;
    }

    public List<Product> findProductByCity(String city){
        logger.info("Searching products by city");
        List<Product> products = productRepository.findProductByCity(city);
        return products;
    }

    public List<ReservationDTO> findReservationByProductId(Long id){
        logger.info("Fetching all reservations that the product have");
        List<Reservation> reservations = productRepository.findReservationByProductId(id);
        List<ReservationDTO> r = new ArrayList<>();
        for (Reservation reservation: reservations) {
            r.add(mapper.convertValue(reservation,ReservationDTO.class));
        }
        return r;
    }

    public List<Product> listNotAvailableProductsByDate(Date startDate, Date endDate){
        logger.info("List of not availables products filtered by date: " + startDate + " - " + endDate);
        return productRepository.listNotAvailableProductsByDate(startDate, endDate);
    }

    public List<Product> listAvailableProductsByDate(Date startDate, Date endDate){
        List<Product> products = productRepository.findAll();
        List<Product> reservedProducts = productRepository.listNotAvailableProductsByDate(startDate, endDate);
        for(Product rp : reservedProducts) {
            products.remove(rp);
        }
        return products;
    }


}
