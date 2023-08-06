package com.example.erp.request;

import lombok.Data;

import java.util.UUID;

@Data
public class BillRequest {
    private UUID orderUUID;
}
