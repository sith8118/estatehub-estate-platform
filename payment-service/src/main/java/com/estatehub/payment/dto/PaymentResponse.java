package com.estatehub.payment.dto;

import com.estatehub.payment.model.Payment.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response object containing payment processing results")
public class PaymentResponse {

    @Schema(description = "Internal Payment ID", example = "1")
    private Long id;

    @Schema(description = "Unique Transaction Identifier from Gateway", example = "tx_9d8ef921-2a9a...")
    private String transactionId;

    @Schema(description = "Final Status of Payment", example = "SUCCESS")
    private PaymentStatus paymentStatus;

    @Schema(description = "Result Message", example = "Payment processed successfully.")
    private String message;
}
