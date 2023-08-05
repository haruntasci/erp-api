package com.example.erp.controller;

import com.example.erp.model.Product;
import com.example.erp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        return new ResponseEntity<>(productService.createOneProduct(product), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
        return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteProductByUUID(@PathVariable UUID uuid) {
        return new ResponseEntity<>(productService.deleteProductByUUID(uuid), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Product> updateProductByUUID(@RequestBody Product product, @PathVariable UUID uuid) {
        return new ResponseEntity<>(productService.updateProduct(product, uuid), HttpStatus.OK);
    }


}
