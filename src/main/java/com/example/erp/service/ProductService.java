package com.example.erp.service;

import com.example.erp.model.KeyValue;
import com.example.erp.model.Product;
import com.example.erp.repository.ProductRepository;
import com.example.erp.request.ProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final KeyValueService keyValueService;

    public ProductService(ProductRepository productRepository, KeyValueService keyValueService) {
        this.productRepository = productRepository;
        this.keyValueService = keyValueService;
    }

    public Product createOneProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setKDVApplied(productRequest.isKDVApplied());
        KeyValue keyValue = keyValueService.getValueByKey(productRequest.getKdvString());
        if (keyValue != null) {
            product.setKDV(keyValue);
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String name) {
        Product product = productRepository.findFirstByNameIgnoreCase(name);
        return product;
    }

    public Product updateProduct(ProductRequest product, UUID uuid) {
        Product productToUpdate = productRepository.findProductByUuid(uuid);
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setKDVApplied(product.isKDVApplied());
        KeyValue keyValue = keyValueService.getValueByKey(product.getKdvString());
        productToUpdate.setKDV(keyValue);
        return productRepository.save(productToUpdate);
    }

    @Transactional
    public String deleteProductByUUID(UUID uuid) {
        if (productRepository.deleteByUuid(uuid) == 1) {
            return "Delete success";
        } else {
            return "Delete error";
        }
    }

}
