package com.estatehub.booking.controller;

import com.estatehub.booking.dto.ErrorResponse;
import com.estatehub.booking.dto.InquiryRequest;
import com.estatehub.booking.dto.InquiryResponse;
import com.estatehub.booking.service.InquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inquiries")
@RequiredArgsConstructor
@Tag(name = "Inquiry", description = "The Inquiry API for managing property inquiries")
@SecurityRequirement(name = "ApiKeyAuth")
public class InquiryController {

    private final InquiryService inquiryService;

    @Operation(summary = "Submit an inquiry", description = "Submits a new property inquiry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inquiry submitted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request arguments",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    })
    @PostMapping
    public ResponseEntity<InquiryResponse> submitInquiry(@Valid @RequestBody InquiryRequest request) {
        return new ResponseEntity<>(inquiryService.submitInquiry(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all inquiries", description = "Retrieves a list of all inquiries")
    @GetMapping
    public ResponseEntity<List<InquiryResponse>> getAllInquiries() {
        return ResponseEntity.ok(inquiryService.getAllInquiries());
    }

    @Operation(summary = "Get inquiry by ID", description = "Retrieves a specific inquiry by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inquiry retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Inquiry not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<InquiryResponse> getInquiryById(@PathVariable Long id) {
        return ResponseEntity.ok(inquiryService.getInquiryById(id));
    }

    @Operation(summary = "Get property inquiries", description = "Get all inquiries made for a specific property")
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<InquiryResponse>> getInquiriesByPropertyId(@PathVariable Long propertyId) {
        return ResponseEntity.ok(inquiryService.getInquiriesByPropertyId(propertyId));
    }
}
