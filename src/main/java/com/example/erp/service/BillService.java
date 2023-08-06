package com.example.erp.service;

import com.example.erp.model.Bill;
import com.example.erp.model.Customer;
import com.example.erp.model.Order;
import com.example.erp.model.OrderItem;
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
        calculateAmounts(order, bill);
        return repository.save(bill);
    }

    private void calculateAmounts(Order order, Bill bill) {
        double rawAmount = 0;
        double totalAmount = 0;
        double kdvAmount = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getProduct().isKDVApplied()) {
                rawAmount += (orderItem.getOrderPrice() / orderItem.getProduct().getKDV().getValue())
                        * orderItem.getQuantity();
                totalAmount += orderItem.getOrderPrice() * orderItem.getQuantity();
            } else {
                rawAmount += orderItem.getOrderPrice() * orderItem.getQuantity();
                totalAmount += orderItem.getOrderPrice()
                        * orderItem.getProduct().getKDV().getValue()
                        * orderItem.getQuantity();
            }
        }
        rawAmount = (double) Math.round(rawAmount * 100) / 100;
        totalAmount = (double) Math.round(totalAmount * 100) / 100;
        kdvAmount = totalAmount - rawAmount;
        kdvAmount = (double) Math.round(kdvAmount * 100) / 100;
        bill.setRawAmount(rawAmount);
        bill.setTotalAmount(totalAmount);
        bill.setKdvAmount(kdvAmount);

    }


    public List<Bill> getAllBills() {
        return repository.findAll();
    }
}
