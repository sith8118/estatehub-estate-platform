package com.estatehub.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryRequest {

    @NotNull(message = "Property ID is required")
    private String propertyId;

    @NotNull(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Message and details cannot be blank")
    private String message;
}
