package com.estatehub.booking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquiries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Inquiry entity for customer property inquiries")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Property ID related to the inquiry")
    @Column(nullable = false)
    private Long propertyId;

    @Schema(description = "Customer ID who submitted the inquiry")
    @Column(nullable = false)
    private Long customerId;

    @Schema(description = "Customer inquiry message")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Schema(description = "Indicates whether the inquiry has been responded to")
    @Column(nullable = false)
    private boolean responded;

    @Schema(description = "Inquiry creation timestamp")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
