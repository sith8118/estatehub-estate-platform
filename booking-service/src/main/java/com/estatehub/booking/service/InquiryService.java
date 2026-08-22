package com.estatehub.booking.service;

import com.estatehub.booking.dto.InquiryRequest;
import com.estatehub.booking.dto.InquiryResponse;

import java.util.List;

public interface InquiryService {
    
    InquiryResponse submitInquiry(InquiryRequest request);
    
    List<InquiryResponse> getAllInquiries();
    
    InquiryResponse getInquiryById(String id);
    
    List<InquiryResponse> getInquiriesByPropertyId(String propertyId);
}
