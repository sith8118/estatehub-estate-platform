package com.estatehub.property.service;

import com.estatehub.property.dto.PropertyRequest;
import com.estatehub.property.dto.PropertyResponse;
import com.estatehub.property.model.Property.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface PropertyService {
    
    PropertyResponse createProperty(PropertyRequest request);
    
    Page<PropertyResponse> getAllProperties(Pageable pageable);
    
    PropertyResponse getPropertyById(Long id);
    
    PropertyResponse updateProperty(Long id, PropertyRequest request);
    
    void deleteProperty(Long id);
    
    List<PropertyResponse> searchProperties(String location, BigDecimal minPrice, BigDecimal maxPrice, PropertyType propertyType, Integer bedrooms);
}
