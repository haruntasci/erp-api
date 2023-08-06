package com.example.erp.service;

import com.example.erp.model.Bill;
import com.example.erp.model.Order;
import com.example.erp.model.OrderItem;
import com.example.erp.repository.BillRepository;
import com.example.erp.repository.OrderRepository;
import com.example.erp.request.BillRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final OrderRepository orderRepository;
    private final PDFGeneratorService pdfGeneratorService;

    public BillService(BillRepository repository, OrderRepository orderRepository, PDFGeneratorService pdfGeneratorService) {
        this.billRepository = repository;
        this.orderRepository = orderRepository;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    public Bill createOneBill(BillRequest billRequest) {

        Bill bill = new Bill();
        Order order = orderRepository.findByUuid(billRequest.getOrderUUID());
        bill.setOrder(order);
        calculateAmounts(order, bill);
        return billRepository.save(bill);
    }

    public Bill getBillByOrderUUID(UUID orderUUID) {
        return billRepository.findByOrderUuid(orderUUID);
    }

    private void calculateAmounts(Order order, Bill bill) {
        double rawAmount = 0;
        double totalAmount = 0;
        double kdvAmount = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getProduct().isKDVApplied()) {
                rawAmount += (orderItem.getOrderPrice() / orderItem.getProduct().getKDV().getKdvValue())
                        * orderItem.getQuantity();
                totalAmount += orderItem.getOrderPrice() * orderItem.getQuantity();
            } else {
                rawAmount += orderItem.getOrderPrice() * orderItem.getQuantity();
                totalAmount += orderItem.getOrderPrice()
                        * orderItem.getProduct().getKDV().getKdvValue()
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


    public String generateBillPDF(UUID billUUID, HttpServletResponse response) {
        Bill bill = billRepository.findByUuid(billUUID);
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        try {
            pdfGeneratorService.export(response, bill);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Successful";

    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
}
