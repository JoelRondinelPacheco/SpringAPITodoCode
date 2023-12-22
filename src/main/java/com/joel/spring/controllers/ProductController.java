package com.joel.spring.controllers;

import com.joel.spring.dtos.products.ProductEditReqDTO;
import com.joel.spring.dtos.products.ProductPostReqDTO;
import com.joel.spring.entities.Product;
import com.joel.spring.exceptions.NotFoundException;
import com.joel.spring.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductPostReqDTO body) {
        return new ResponseEntity<Product>( this.productService.save(body), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Product> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(@PathVariable String productId) throws NotFoundException {
        Product product = this.productService.getById(productId);
            return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> delete(@PathVariable String productId) {
        this.productService.delete(productId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


    @PutMapping("/edit")
    public ResponseEntity<Product> edit(@RequestBody ProductEditReqDTO body) throws NotFoundException {
        Product product = this.productService.update(body);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/lack_stock/{stock}")
    public List<Product> lackStock(@PathVariable Long stock) {
        return this.productService.getLackStock(stock);
    }
}
