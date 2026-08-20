package com.estatehub.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotNull(message = "Property ID is required")
    private String propertyId;

    @NotNull(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Visit date is required")
    @Future(message = "Visit date must be in the future")
    private LocalDateTime visitDate;

    private String remarks;
}
