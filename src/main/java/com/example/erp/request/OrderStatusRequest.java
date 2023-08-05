package com.example.erp.request;

import com.example.erp.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusRequest {
    private OrderStatus status;
    private Long orderId;
}
