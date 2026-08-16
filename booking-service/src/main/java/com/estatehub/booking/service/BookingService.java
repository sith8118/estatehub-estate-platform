package com.estatehub.booking.service;

import com.estatehub.booking.dto.BookingRequest;
import com.estatehub.booking.dto.BookingResponse;
import com.estatehub.booking.model.Booking.BookingStatus;

import java.util.List;

public interface BookingService {
    
    BookingResponse createBooking(BookingRequest request);
    
    List<BookingResponse> getAllBookings();
    
    BookingResponse getBookingById(Long id);
    
    List<BookingResponse> getBookingsByCustomerId(Long customerId);
    
    BookingResponse updateBookingStatus(Long id, BookingStatus newStatus);
    
    void deleteBooking(Long id);
}
