package com.estatehub.property.dto;

import com.estatehub.property.model.Property.PropertyStatus;
import com.estatehub.property.model.Property.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {

    private Long id;
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
    private Long agentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
