package com.estatehub.property.dto;

import com.estatehub.property.model.Property.PropertyStatus;
import com.estatehub.property.model.Property.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Property type is required")
    private PropertyType propertyType;

    @NotNull(message = "Bedrooms count is required")
    @Positive(message = "Bedrooms must be positive")
    private Integer bedrooms;

    @NotNull(message = "Bathrooms count is required")
    @Positive(message = "Bathrooms must be positive")
    private Integer bathrooms;

    @NotNull(message = "Area is required")
    @Positive(message = "Area must be positive")
    private Double area;

    @NotNull(message = "Status is required")
    private PropertyStatus status;

    private String imageUrl;

    @NotNull(message = "Agent ID is required")
    private String agentId;
}
