package com.estatehub.booking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Booking entity for property visit management")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Property ID related to the booking")
    @Column(nullable = false)
    private Long propertyId;

    @Schema(description = "Customer ID who created the booking")
    @Column(nullable = false)
    private Long customerId;

    @Schema(description = "Scheduled visit date and time")
    @Column(nullable = false)
    private LocalDateTime visitDate;

    @Schema(description = "Current booking status")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Schema(description = "Additional remarks about the booking")
    @Column(columnDefinition = "TEXT")
    private String remarks;

    @Schema(description = "Booking creation timestamp")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Last updated timestamp")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
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