package com.example.erp.controller;

import com.example.erp.model.Customer;
import com.example.erp.request.CustomerRequest;
import com.example.erp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequest request) {
        return new ResponseEntity<>(customerService.createOneCustomer(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomerByUUID(@PathVariable String name) {
        return new ResponseEntity<>(customerService.getCustomerByName(name), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Customer> updateCustomerByUUID(@RequestBody CustomerRequest request, @PathVariable UUID uuid) {
        return new ResponseEntity<>(customerService.updateCustomerByUUID(request, uuid), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteCustomerByUUID(@PathVariable UUID uuid) {
        return new ResponseEntity<>(customerService.deleteCustomerByUUID(uuid), HttpStatus.OK);
    }

}
