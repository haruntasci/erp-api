package com.example.erp.request;

import lombok.Data;

@Data
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
