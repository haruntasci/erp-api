package com.example.erp.service;

import com.example.erp.model.Product;
import com.example.erp.model.Stock;
import com.example.erp.repository.ProductRepository;
import com.example.erp.repository.StockRepository;
import com.example.erp.request.StockRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    public StockService(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    public Stock createOneStock(StockRequest stockRequest) {
        Product product = productRepository.findFirstByNameIgnoreCase(stockRequest.getProductName());
        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setQuantity(stockRequest.getQuantity());
        return stockRepository.save(stock);
    }


    public void changeProductQuantityInStock(Product product, int quantity) {
        Stock stock = stockRepository.findStockByProduct(product);
        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepository.save(stock);
    }
    @Transactional
    public String deleteProductByUUID(UUID uuid) {
        if (stockRepository.deleteByUuid(uuid) == 1) {
            return "Delete success";
        } else {
            return "Delete error";
        }
    }

    public Stock getStockByProduct(Product product) {
        return stockRepository.findStockByProduct(product);
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

}
