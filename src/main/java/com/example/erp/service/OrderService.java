package com.example.erp.service;

import com.example.erp.enums.OrderStatus;
import com.example.erp.exception.NoProductsInStockException;
import com.example.erp.model.*;
import com.example.erp.repository.CustomerRepository;
import com.example.erp.repository.OrderItemRepository;
import com.example.erp.repository.OrderRepository;
import com.example.erp.repository.ProductRepository;
import com.example.erp.request.BillRequest;
import com.example.erp.request.OrderItemRequest;
import com.example.erp.request.OrderRequest;
import com.example.erp.request.OrderStatusRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final StockService stockService;
    private final BillService billService;
    private final OrderItemRepository orderItemRepository;


    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        ProductRepository productRepository, StockService stockService, BillService billService,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.stockService = stockService;
        this.billService = billService;
        this.orderItemRepository = orderItemRepository;
    }

    public Order createOneOrder(OrderRequest orderRequest) {
        Order order = new Order();
        Customer customer = customerRepository.findCustomerByUuid(orderRequest.getCustomerUUID());
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    public Order addOrderItemToOrder(OrderItemRequest request) {
        try {
            Product product = productRepository.findFirstByNameIgnoreCase(request.getProductName());
            Stock stock = stockService.getStockByProduct(product);
            if (stock.getQuantity() < request.getQuantity()) {
                throw new NoProductsInStockException("Number of products in stock: " + stock.getQuantity()
                        + " The number of products you are trying to add: " + request.getQuantity());
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderPrice(product.getPrice());
            orderItem.setQuantity(request.getQuantity());
            orderItem.setProduct(product);
            OrderItem newOrderItem = orderItemRepository.save(orderItem);
            Order order = orderRepository.findByUuid(request.getOrderUUID());
            order.getOrderItems().add(newOrderItem);

            return orderRepository.save(order);
        } catch (NoProductsInStockException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String changeOrderStatus(OrderStatusRequest request) {
        Order order = orderRepository.findByUuid(request.getOrderUUID());
        order.setOrderStatus(request.getStatus());
        Order savedOrder = orderRepository.save(order);
        if (request.getStatus().equals(OrderStatus.APPROVED)) {
            updateProductQuantity(savedOrder);
            createBill(request);
            return "Order approved and bill created.";
        } else {
            return request.getStatus().toString();
        }

    }

    private void createBill(OrderStatusRequest request) {
        BillRequest billRequest = new BillRequest();
        billRequest.setOrderUUID(request.getOrderUUID());
        billService.createOneBill(billRequest);
    }

    private void updateProductQuantity(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            stockService.changeProductQuantityInStock(orderItem.getProduct(), orderItem.getQuantity());
        }
    }

    @Transactional
    public String deleteProductByUUID(UUID uuid) {
        if (orderRepository.deleteByUuid(uuid) == 1) {
            return "Delete success";
        } else {
            return "Delete error";
        }
    }


}
