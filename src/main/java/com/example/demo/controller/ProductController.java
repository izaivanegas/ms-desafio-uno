package com.example.demo.controller;

import com.example.demo.exeption.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    /**
     * Constructor for the ProductController class.
     *
     * @param productRepository the repository used to interact with the Product data
     */
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a ResponseEntity containing*/
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return   ResponseEntity.ok(this.productRepository.findAll());
    }

    /**
     * Retrieves a product by*/
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(this.productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id)));
    }

    /**
     * Creates a new product and stores it in the database.
     *
     * @param product the product details to be created
     * @return a ResponseEntity containing the created product object
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(this.productRepository.save(product));
    }

    /**
     * Updates an existing product's details based on the provided product information and ID.
     *
     * @param productDetails the updated details of the product
     * @param id the ID of the product to update
     * @return a ResponseEntity containing the updated product object
     * @throws ProductNotFoundException if a product with the given ID is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productDetails, @PathVariable Long id){
        Product productUpdate = this.productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        productUpdate.setName(productDetails.getName());
        productUpdate.setPrice(productDetails.getPrice());
        productUpdate.setQuantity(productDetails.getQuantity());
        return ResponseEntity.ok(this.productRepository.save(productUpdate));
    }

    /**
     * Deletes a product from the database using its ID.
     *
     * @param id the ID of the product to be deleted
     * @return a confirmation message indicating the successful deletion of the product
     * @throws ProductNotFoundException if a product with the given ID is not found
     */
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
         Product productDelete = this.productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
         this.productRepository.delete(productDelete);
         return String.format("Product %d deleted successfully",id);
    }



}
