package com.example.erp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem extends Base {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private int quantity;

    @Column
    private double orderPrice;


}