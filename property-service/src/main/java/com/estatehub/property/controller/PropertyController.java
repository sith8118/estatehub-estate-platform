package com.estatehub.property.controller;

import com.estatehub.property.dto.ErrorResponse;
import com.estatehub.property.dto.PropertyRequest;
import com.estatehub.property.dto.PropertyResponse;
import com.estatehub.property.model.Property.PropertyType;
import com.estatehub.property.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
@Tag(name = "Property", description = "The Property API for managing real estate properties")
@SecurityRequirement(name = "ApiKeyAuth")
public class PropertyController {

    private final PropertyService propertyService;

    @Operation(summary = "Create a new property", description = "Creates a new property listing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Property created successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PropertyResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request arguments",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    })
    @PostMapping
    public ResponseEntity<PropertyResponse> createProperty(@Valid @RequestBody PropertyRequest request) {
        return new ResponseEntity<>(propertyService.createProperty(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all properties", description = "Retrieves a paginated list of all properties")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public ResponseEntity<Page<PropertyResponse>> getAllProperties(Pageable pageable) {
        return ResponseEntity.ok(propertyService.getAllProperties(pageable));
    }

    @Operation(summary = "Get property by ID", description = "Retrieves a specific property by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Property not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @Operation(summary = "Update an existing property", description = "Updates a property listing by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request arguments"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> updateProperty(@PathVariable Long id, @Valid @RequestBody PropertyRequest request) {
        return ResponseEntity.ok(propertyService.updateProperty(id, request));
    }

    @Operation(summary = "Delete a property", description = "Deletes a property by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Property deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search properties", description = "Search for properties using query filters")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matched properties list")
    @GetMapping("/search")
    public ResponseEntity<List<PropertyResponse>> searchProperties(
            @Parameter(description = "Location of the property") @RequestParam(required = false) String location,
            @Parameter(description = "Minimum price") @RequestParam(required = false) BigDecimal minPrice,
            @Parameter(description = "Maximum price") @RequestParam(required = false) BigDecimal maxPrice,
            @Parameter(description = "Type of the property") @RequestParam(required = false) PropertyType propertyType,
            @Parameter(description = "Minimum number of bedrooms") @RequestParam(required = false) Integer bedrooms) {
        
        return ResponseEntity.ok(propertyService.searchProperties(location, minPrice, maxPrice, propertyType, bedrooms));
    }
}
