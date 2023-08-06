package com.example.erp.service;

import com.example.erp.model.Bill;
import com.example.erp.model.OrderItem;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response, Bill bill) throws IOException {

        try (OutputStream outputStream = response.getOutputStream()) {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);

            document.open();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Font titleFont = new Font(Font.HELVETICA, 24, Font.BOLD);
            Paragraph title = new Paragraph("Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);

            Table firstTable = new Table(2);
            firstTable.setWidths(new float[]{5, 5});
            firstTable.setBorder(0);
            Font boldText = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font plainText = new Font(Font.HELVETICA, 10);

            Cell infoCell = new Cell(new Paragraph("Invoice ID: ", boldText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            infoCell = new Cell(new Paragraph(String.valueOf(bill.getUuid()), plainText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            infoCell = new Cell(new Paragraph("Invoice Date: ", boldText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            infoCell = new Cell(new Paragraph(formatter.format(bill.getUpdatedDate()), plainText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            infoCell = new Cell(new Paragraph("Customer: ", boldText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            infoCell = new Cell(new Paragraph(bill.getOrder().getCustomer().getFirstName() + " "
                    + bill.getOrder().getCustomer().getLastName(), plainText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);


            infoCell = new Cell(new Paragraph("Address: ", boldText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            infoCell = new Cell(new Paragraph(bill.getOrder().getCustomer().getAddress(), plainText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            infoCell = new Cell(new Paragraph("Email: ", boldText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            infoCell = new Cell(new Paragraph(bill.getOrder().getCustomer().getEmail(), plainText));
            infoCell.setBorder(0);
            firstTable.addCell(infoCell);

            document.add(firstTable);

            Table table = new Table(4);
            table.setWidths(new float[]{3, 2, 2, 3});
            table.setPadding(5);
            table.setBorderWidth(1);
            table.setBorderColor(new Color(192, 192, 192));
            table.setBackgroundColor(new Color(240, 240, 240));

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.US));


            Font headerFont = new Font(Font.HELVETICA, 10);
            Cell headerCell = new Cell(new Paragraph("Product Name", headerFont));
            headerCell.setHeader(true);
            headerCell.setBackgroundColor(new Color(192, 192, 192));
            table.addCell(headerCell);

            headerCell = new Cell(new Paragraph("Quantity", headerFont));
            headerCell.setBackgroundColor(new Color(192, 192, 192));
            table.addCell(headerCell);

            headerCell = new Cell(new Paragraph("KDV Applied", headerFont));
            headerCell.setBackgroundColor(new Color(192, 192, 192));
            table.addCell(headerCell);

            headerCell = new Cell(new Paragraph("Ordered Price (TL)", headerFont));
            headerCell.setBackgroundColor(new Color(192, 192, 192));
            table.addCell(headerCell);
            Font infoFont = new Font(Font.HELVETICA, 10, Font.BOLD);
            for (OrderItem orderItem : bill.getOrder().getOrderItems()) {
                String kdvAdded = orderItem.getProduct().isKDVApplied() ? "YES" : "NO";

                table.addCell(new Cell(new Paragraph(orderItem.getProduct().getName(), infoFont)));
                table.addCell(new Cell(new Paragraph(String.valueOf(orderItem.getQuantity()), infoFont)));
                table.addCell(new Cell(new Paragraph(kdvAdded, infoFont)));
                table.addCell(new Cell(new Paragraph(decimalFormat.format(orderItem.getOrderPrice()) + " TL", infoFont)));
            }

            document.add(table);

            Table lastTable = new Table(3);
            lastTable.setWidths(new float[]{2, 4, 4});
            lastTable.setBorder(0);
            Cell emptyCell = new Cell("");
            emptyCell.setColspan(2);
            emptyCell.setBorder(0);
            lastTable.addCell(emptyCell);
            Cell amountCell = new Cell(new Paragraph("Tax-free Amount: " + decimalFormat.format(bill.getRawAmount())
                    + " TL", plainText));
            amountCell.setBorder(0);
            lastTable.addCell(amountCell);
            lastTable.addCell(emptyCell);
            amountCell = new Cell(new Paragraph("KDV Amount: " + decimalFormat.format(bill.getKdvAmount())
                    + " TL", plainText));

            amountCell.setBorder(0);
            lastTable.addCell(amountCell);
            lastTable.addCell(emptyCell);
            amountCell = new Cell(new Paragraph("Total Amount: " + decimalFormat.format(bill.getTotalAmount())
                    + " TL", boldText));
            amountCell.setBorder(0);
            lastTable.addCell(amountCell);


            document.add(lastTable);
            document.close();
        }


    }
}
