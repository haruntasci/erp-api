package com.example.erp.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    private double price;

    private boolean isKDVApplied = false;

    private String kdvString;
}
