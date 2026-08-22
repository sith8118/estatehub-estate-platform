package com.estatehub.payment.controller;

import com.estatehub.payment.dto.PaymentRequest;
import com.estatehub.payment.dto.PaymentResponse;
import com.estatehub.payment.model.Payment;
import com.estatehub.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payment Management", description = "Endpoints for processing and retrieving payments")
@SecurityRequirement(name = "ApiKeyAuth")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Process a new payment", description = "Simulates payment processing and returns success status with transaction ID.")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment by ID", description = "Retrieves an existing payment by its internal database ID.")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/booking/{bookingId}")
    @Operation(summary = "Get payment by Booking ID", description = "Retrieves an existing payment by the associated Booking ID.")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable String bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentByBookingId(bookingId));
    }

    @GetMapping("/history")
    @Operation(summary = "Get payment history", description = "Retrieves a paginated list of all payments.")
    public ResponseEntity<Page<Payment>> getPaymentHistory(Pageable pageable) {
        return ResponseEntity.ok(paymentService.getPaymentHistory(pageable));
    }

    @GetMapping(value = "/{id}/invoice", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Download Invoice PDF", description = "Generates and returns a downloadable PDF invoice for a given payment ID.")
    public ResponseEntity<byte[]> getPaymentInvoice(@PathVariable String id) {
        byte[] pdfBytes = paymentService.generateInvoice(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "invoice-" + id + ".pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
