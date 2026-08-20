package com.estatehub.booking.controller;

import com.estatehub.booking.dto.BookingRequest;
import com.estatehub.booking.dto.BookingResponse;
import com.estatehub.booking.dto.ErrorResponse;
import com.estatehub.booking.model.Booking.BookingStatus;
import com.estatehub.booking.service.BookingService;
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
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking", description = "The Booking API for managing property visit reservations")
@SecurityRequirement(name = "ApiKeyAuth")
public class BookingController {

    private final BookingService bookingService;

    @Operation(
    summary = "Delete a booking",
    description = "Deletes an existing booking record by its unique identifier"
)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request arguments",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    })
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        return new ResponseEntity<>(bookingService.createBooking(request), HttpStatus.CREATED);
    }

    @Operation(
    summary = "Get all bookings",
    description = "Retrieves a complete list of booking records"
)
@ApiResponse(responseCode = "200", description = "Bookings retrieved successfully")
    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Operation(summary = "Get booking by ID", description = "Retrieves a specific booking by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable String id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @Operation(summary = "Get customer bookings", description = "Get all bookings made by a specific customer")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomerId(customerId));
    }

    @Operation(summary = "Update booking status", description = "Updates a booking's lifecycle status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBookingStatus(
            @PathVariable String id, @RequestParam BookingStatus newStatus) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, newStatus));
    }

    @Operation(summary = "Delete a booking", description = "Deletes and thoroughly removes a booking reservation")
    @ApiResponse(responseCode = "204", description = "Booking deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
