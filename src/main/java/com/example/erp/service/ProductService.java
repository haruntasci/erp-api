package com.example.erp.service;

import com.example.erp.model.Product;
import com.example.erp.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createOneProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String name) {
        Product product = productRepository.findFirstByNameIgnoreCase(name);
        return product;
    }

    public Product updateProduct(Product product, UUID uuid) {
        Product productToUpdate = productRepository.findProductByUuid(uuid);
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setKDVApplied(product.isKDVApplied());
        productToUpdate.setKdvType(product.getKdvType());
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
