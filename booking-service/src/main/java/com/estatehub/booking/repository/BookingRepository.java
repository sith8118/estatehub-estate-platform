package com.estatehub.booking.repository;

import com.estatehub.booking.model.Booking;
import com.estatehub.booking.model.Booking.BookingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByCustomerId(String customerId);

    List<Booking> findByPropertyId(String propertyId);

    List<Booking> findByStatus(BookingStatus status);

    List<Booking> findByCustomerIdAndStatus(String customerId, BookingStatus status);
}