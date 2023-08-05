package com.example.erp.service;

import com.example.erp.model.Customer;
import com.example.erp.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createOneCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.findCustomerByNameIgnoreCase(name);
    }

    public Customer updateCustomerByUUID(Customer customer, UUID uuid) {
        Customer customerToUpdate = customerRepository.findCustomerByUuid(uuid);
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        return customerRepository.save(customerToUpdate);
    }

    public String deleteCustomerByUUID(UUID uuid) {
        if (customerRepository.deleteByUuid(uuid) == 1) {
            return "Delete success";
        } else {
            return "Delete error";
        }
    }
}
