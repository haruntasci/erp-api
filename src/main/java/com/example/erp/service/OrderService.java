package com.example.erp.service;

import com.example.erp.enums.OrderStatus;
import com.example.erp.model.*;
import com.example.erp.repository.CustomerRepository;
import com.example.erp.repository.OrderRepository;
import com.example.erp.repository.ProductRepository;
import com.example.erp.request.BillRequest;
import com.example.erp.request.OrderRequest;
import com.example.erp.request.OrderStatusRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final StockService stockService;
    private final BillService billService;


    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        ProductRepository productRepository, StockService stockService, BillService billService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.stockService = stockService;
        this.billService = billService;
    }

    public Order createOneOrder(OrderRequest orderRequest) {
        Order order = new Order();
        Customer customer = customerRepository.findCustomerByNameIgnoreCase(orderRequest.getCustomerName());
        Product product = productRepository.findFirstByNameIgnoreCase(orderRequest.getProductName());
        order.setQuantity(orderRequest.getQuantity());
        order.setCustomer(customer);
        order.setProduct(product);
        order.setOrderPrice(product.getPrice());
        stockService.changeProductQuantityInStock(product, orderRequest.getQuantity());


        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String changeOrderStatus(OrderStatusRequest request) {
        Order order = orderRepository.findById(request.getOrderId()).get();
        order.setOrderStatus(request.getStatus());
        orderRepository.save(order);
        if (request.getStatus().equals(OrderStatus.APPROVED)) {
            BillRequest billRequest = new BillRequest();
            billRequest.setOrderId(request.getOrderId());
            billService.createOneBill(billRequest);
            return "Order approved and bill created.";
        } else {
            return request.getStatus().toString();
        }


    }


}
