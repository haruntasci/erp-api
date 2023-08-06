package com.example.erp.service;

import com.example.erp.model.Customer;
import com.example.erp.repository.CustomerRepository;
import com.example.erp.request.CustomerRequest;
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

    public Customer createOneCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.findCustomerByFirstNameIgnoreCase(name);
    }

    public Customer updateCustomerByUUID(CustomerRequest request, UUID uuid) {
        Customer customerToUpdate = customerRepository.findCustomerByUuid(uuid);
        customerToUpdate.setFirstName(request.getFirstName());
        customerToUpdate.setLastName(request.getLastName());
        customerToUpdate.setEmail(request.getEmail());
        customerToUpdate.setAddress(request.getAddress());
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
