package com.estatehub.payment.service;

import com.estatehub.payment.dto.NotificationRequest;
import com.estatehub.payment.dto.PaymentRequest;
import com.estatehub.payment.dto.PaymentResponse;
import com.estatehub.payment.model.NotificationLog;
import com.estatehub.payment.model.Payment;
import com.estatehub.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final NotificationService notificationService;
    private final InvoiceService invoiceService;

    @Override
    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        log.info("Processing payment for booking {}", request.getBookingId());

        // Check if payment already exists for this booking
        paymentRepository.findByBookingId(request.getBookingId()).ifPresent(p -> {
            throw new IllegalArgumentException("Payment already processed for booking ID: " + request.getBookingId());
        });

        // Simulate Gateway Logic
        String transactionId = "tx_" + UUID.randomUUID().toString();
        Payment.PaymentStatus status = Payment.PaymentStatus.SUCCESS; // Mock success

        Payment payment = Payment.builder()
                .bookingId(request.getBookingId())
                .customerId(request.getCustomerId())
                .customerEmail(request.getCustomerEmail())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(status)
                .transactionId(transactionId)
                .build();

        payment = paymentRepository.save(payment);
        log.info("Payment saved with ID {}", payment.getId());

        if (status == Payment.PaymentStatus.SUCCESS) {
            // Trigger background Email Notification
            NotificationRequest emailReq = new NotificationRequest();
            emailReq.setRecipient(payment.getCustomerEmail());
            emailReq.setType(NotificationLog.NotificationType.EMAIL);
            emailReq.setSubject("Payment Confirmation (Booking " + payment.getBookingId() + ")");
            emailReq.setMessage("Hello, your payment of $" + payment.getAmount() + " was processed successfully. Transaction ID: " + transactionId);
            
            // Note: in a real async environment this could be @Async
            notificationService.sendEmail(emailReq);
        }

        return PaymentResponse.builder()
                .id(payment.getId())
                .transactionId(transactionId)
                .paymentStatus(status)
                .message("Payment processed " + status.name().toLowerCase())
                .build();
    }

    @Override
    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with ID: " + id));
    }

    @Override
    public Payment getPaymentByBookingId(String bookingId) {
        return paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found for Booking ID: " + bookingId));
    }

    @Override
    public Page<Payment> getPaymentHistory(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public byte[] generateInvoice(String id) {
        Payment payment = getPaymentById(id);
        return invoiceService.generateInvoicePdf(payment);
    }
}
