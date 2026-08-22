package com.estatehub.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Booking entity for property visit management")
public class Booking {

    @Id
    private String id;

    @Schema(description = "Property ID related to the booking")
    private String propertyId;

    @Schema(description = "Customer ID who created the booking")
    private String customerId;

    @Schema(description = "Scheduled visit date and time")
    private LocalDateTime visitDate;

    @Schema(description = "Current booking status")
    private BookingStatus status;

    @Schema(description = "Additional remarks about the booking")
    private String remarks;

    @Schema(description = "Booking creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last updated timestamp")
    private LocalDateTime updatedAt;

    
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }
}