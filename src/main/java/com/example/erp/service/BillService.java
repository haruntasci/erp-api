package com.example.erp.service;

import com.example.erp.model.Bill;
import com.example.erp.model.Customer;
import com.example.erp.model.Order;
import com.example.erp.repository.BillRepository;
import com.example.erp.repository.CustomerRepository;
import com.example.erp.repository.OrderRepository;
import com.example.erp.request.BillRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private final BillRepository repository;
    private final OrderRepository orderRepository;

    public BillService(BillRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    public Bill createOneBill(BillRequest billRequest) {
        Bill bill = new Bill();
        Order order = orderRepository.findById(billRequest.getOrderId()).get();
        bill.setOrder(order);
        bill.setTotalAmount(order.getQuantity() * order.getOrderPrice());
        return repository.save(bill);
    }

    public List<Bill> getAllBills() {
        return repository.findAll();
    }
}
