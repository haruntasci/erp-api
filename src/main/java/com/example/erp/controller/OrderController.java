package com.example.erp.controller;

import com.example.erp.enums.OrderStatus;
import com.example.erp.model.Order;
import com.example.erp.request.OrderRequest;
import com.example.erp.request.OrderStatusRequest;
import com.example.erp.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.createOneOrder(orderRequest), HttpStatus.OK);
    }

    @PostMapping("/change-order-status")
    public ResponseEntity<String> changeOrderStatus(@RequestBody OrderStatusRequest orderStatusRequest) {
        return new ResponseEntity<>(orderService.changeOrderStatus(orderStatusRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
}
