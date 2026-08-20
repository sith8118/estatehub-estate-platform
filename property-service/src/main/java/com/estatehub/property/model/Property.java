package com.estatehub.property.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    private String id;

    private String title;

    private String description;

    private BigDecimal price;

    private String location;

    private PropertyType propertyType;

    private Integer bedrooms;

    private Integer bathrooms;

    private Double area;

    private PropertyStatus status;

    private String imageUrl;

    private String agentId;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public enum PropertyType {
        VILLA, APARTMENT, HOUSE, LAND
    }

    public enum PropertyStatus {
        AVAILABLE, SOLD, RENTED
    }
}
