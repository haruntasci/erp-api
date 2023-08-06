package com.example.erp.controller;

import com.example.erp.model.OrderItem;
import com.example.erp.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return new ResponseEntity<>(orderItemService.gelAllOrderItems(), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteOrderItemByUUID(@PathVariable UUID uuid) {
        return new ResponseEntity<>(orderItemService.deleteOrderItemByUUID(uuid), HttpStatus.OK);
    }
}
