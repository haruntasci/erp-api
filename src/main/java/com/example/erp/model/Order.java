package com.example.erp.model;

import com.example.erp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

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

    @Column
    @ManyToOne
    private Customer customer;

    @Column
    @ManyToOne
    private Product product;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.WAITING_FOR_APPROVAL;

    @Column
    private int quantity;

    @Column
    private double orderPrice;


}
