package com.example.erp.service;

import com.example.erp.model.OrderItem;
import com.example.erp.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> gelAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public String deleteOrderItemByUUID(UUID uuid) {
        if (orderItemRepository.deleteByUuid(uuid) == 1) {
            return "Delete success";
        } else {
            return "Delete error";
        }
    }
}
