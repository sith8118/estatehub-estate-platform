package com.estatehub.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryResponse {

    private Long id;
    private Long propertyId;
    private Long customerId;
    private String message;
    private boolean responded;
    private LocalDateTime createdAt;
}
