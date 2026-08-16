package com.estatehub.payment.service;

import com.estatehub.payment.model.Payment;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class InvoiceService {

    public byte[] generateInvoicePdf(Payment payment) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);
            Paragraph title = new Paragraph("ESTATEHUB INVOICE", titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            
            document.add(new Paragraph(" "));
            
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            
            document.add(new Paragraph("Invoice Number: INV-" + payment.getId(), contentFont));
            document.add(new Paragraph("Date: " + payment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), contentFont));
            document.add(new Paragraph("Transaction ID: " + payment.getTransactionId(), contentFont));
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph("--- Customer Details ---", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            document.add(new Paragraph("Customer ID: " + payment.getCustomerId(), contentFont));
            document.add(new Paragraph("Email: " + payment.getCustomerEmail(), contentFont));
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph("--- Payment Details ---", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            document.add(new Paragraph("Booking ID: " + payment.getBookingId(), contentFont));
            document.add(new Paragraph("Method: " + payment.getPaymentMethod(), contentFont));
            document.add(new Paragraph("Amount: $" + payment.getAmount(), contentFont));
            document.add(new Paragraph("Status: " + payment.getPaymentStatus(), contentFont));
            
            document.close();
            log.info("Successfully generated PDF invoice for payment ID {}", payment.getId());
            
        } catch (DocumentException e) {
            log.error("Error generating PDF invoice", e);
            throw new RuntimeException("Could not generate invoice PDF", e);
        }
        
        return out.toByteArray();
    }
}
