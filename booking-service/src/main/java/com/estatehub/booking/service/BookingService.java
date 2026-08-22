package com.estatehub.booking.service;

import com.estatehub.booking.dto.BookingRequest;
import com.estatehub.booking.dto.BookingResponse;
import com.estatehub.booking.model.Booking.BookingStatus;

import java.util.List;

public interface BookingService {

    BookingResponse createBooking(BookingRequest request);

    List<BookingResponse> getAllBookings();

    BookingResponse getBookingById(String id);

    List<BookingResponse> getBookingsByCustomerId(String customerId);

    List<BookingResponse> getBookingsByPropertyId(String propertyId);

    List<BookingResponse> getBookingsByStatus(BookingStatus status);

    BookingResponse updateBookingStatus(String id, BookingStatus newStatus);

    void deleteBooking(String id);
}
