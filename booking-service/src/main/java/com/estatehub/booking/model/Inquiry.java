package com.estatehub.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "inquiries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Inquiry entity for customer property inquiries")
public class Inquiry {

    @Id
    private String id;

    @Schema(description = "Property ID related to the inquiry")
    private String propertyId;

    @Schema(description = "Customer ID who submitted the inquiry")
    private String customerId;

    @Schema(description = "Customer inquiry message")
    private String message;

    @Schema(description = "Indicates whether the inquiry has been responded to")
    private boolean responded;

    @Schema(description = "Inquiry creation timestamp")
    private LocalDateTime createdAt;

    
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
