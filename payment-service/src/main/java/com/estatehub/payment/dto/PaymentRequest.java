package com.estatehub.payment.dto;

import com.estatehub.payment.model.Payment.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Request object for processing a new payment")
public class PaymentRequest {

    @NotBlank(message = "Booking ID is required")
    @Schema(description = "Associated Booking ID", example = "BK-100294")
    private String bookingId;

    @NotBlank(message = "Customer ID is required")
    @Schema(description = "Customer ID making the payment", example = "CUST-992")
    private String customerId;

    @NotBlank(message = "Customer Email is required")
    @Email(message = "Must be a valid email format")
    @Schema(description = "Customer's email for receipt", example = "customer@example.com")
    private String customerEmail;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be strictly positive")
    @Schema(description = "Payment Amount", example = "1500.00")
    private BigDecimal amount;

    @NotNull(message = "Payment method is required")
    @Schema(description = "Chosen Payment Method", example = "CREDIT_CARD")
    private PaymentMethod paymentMethod;
}
