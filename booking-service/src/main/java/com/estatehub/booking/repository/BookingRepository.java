package com.estatehub.booking.repository;

import com.estatehub.booking.model.Booking;
import com.estatehub.booking.model.Booking.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findByPropertyId(Long propertyId);

    List<Booking> findByStatus(BookingStatus status);

    List<Booking> findByCustomerIdAndStatus(Long customerId, BookingStatus status);
}