package com.example.erp.model;

import com.example.erp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "order_uuid"
        )
)
@Entity
@Data
@Table(name = "orders")
public class Order extends Base {

    @ManyToOne
    private Customer customer;

    @OneToMany(targetEntity = OrderItem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id",name = "order_id")
    private List<OrderItem> orderItems;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.WAITING_FOR_APPROVAL;

}
