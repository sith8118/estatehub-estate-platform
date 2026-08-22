package com.estatehub.payment.service;

import com.estatehub.payment.dto.PaymentRequest;
import com.estatehub.payment.dto.PaymentResponse;
import com.estatehub.payment.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
    Payment getPaymentById(String id);
    Payment getPaymentByBookingId(String bookingId);
    Page<Payment> getPaymentHistory(Pageable pageable);
    byte[] generateInvoice(String id);
}
