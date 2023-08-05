package com.example.erp.model;

import jakarta.persistence.*;
import lombok.Data;

@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "customer_uuid"
        )
)
@Entity
@Data
@Table(name = "customers")
public class Customer extends Base{

    @Column
    private String name;

    @Column
    private String email;

}