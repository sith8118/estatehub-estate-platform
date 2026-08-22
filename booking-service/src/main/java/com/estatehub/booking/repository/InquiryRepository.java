package com.estatehub.booking.repository;

import com.estatehub.booking.model.Inquiry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends MongoRepository<Inquiry, String> {
    List<Inquiry> findByPropertyId(String propertyId);
}
