package com.estatehub.property.service;

import com.estatehub.property.dto.PropertyRequest;
import com.estatehub.property.dto.PropertyResponse;
import com.estatehub.property.model.Property;
import com.estatehub.property.model.Property.PropertyType;
import com.estatehub.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    public PropertyResponse createProperty(PropertyRequest request) {
        Property property = Property.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .location(request.getLocation())
                .propertyType(request.getPropertyType())
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .area(request.getArea())
                .status(request.getStatus())
                .imageUrl(request.getImageUrl())
                .agentId(request.getAgentId())
                .build();
                
        Property savedProperty = propertyRepository.save(property);
        return mapToResponse(savedProperty);
    }

    @Override
    public Page<PropertyResponse> getAllProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public PropertyResponse getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
        return mapToResponse(property);
    }

    @Override
    public PropertyResponse updateProperty(Long id, PropertyRequest request) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
                
        property.setTitle(request.getTitle());
        property.setDescription(request.getDescription());
        property.setPrice(request.getPrice());
        property.setLocation(request.getLocation());
        property.setPropertyType(request.getPropertyType());
        property.setBedrooms(request.getBedrooms());
        property.setBathrooms(request.getBathrooms());
        property.setArea(request.getArea());
        property.setStatus(request.getStatus());
        property.setImageUrl(request.getImageUrl());
        property.setAgentId(request.getAgentId());
        
        Property updatedProperty = propertyRepository.save(property);
        return mapToResponse(updatedProperty);
    }

    @Override
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
        propertyRepository.delete(property);
    }

    @Override
    public List<PropertyResponse> searchProperties(String location, BigDecimal minPrice, BigDecimal maxPrice, PropertyType propertyType, Integer bedrooms) {
        List<Property> properties = propertyRepository.searchProperties(location, minPrice, maxPrice, propertyType, bedrooms);
        return properties.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    
    private PropertyResponse mapToResponse(Property property) {
        return PropertyResponse.builder()
                .id(property.getId())
                .title(property.getTitle())
                .description(property.getDescription())
                .price(property.getPrice())
                .location(property.getLocation())
                .propertyType(property.getPropertyType())
                .bedrooms(property.getBedrooms())
                .bathrooms(property.getBathrooms())
                .area(property.getArea())
                .status(property.getStatus())
                .imageUrl(property.getImageUrl())
                .agentId(property.getAgentId())
                .createdAt(property.getCreatedAt())
                .updatedAt(property.getUpdatedAt())
                .build();
    }
}
