package com.estatehub.booking.service;

import com.estatehub.booking.dto.InquiryRequest;
import com.estatehub.booking.dto.InquiryResponse;
import com.estatehub.booking.model.Inquiry;
import com.estatehub.booking.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    @Override
    public InquiryResponse submitInquiry(InquiryRequest request) {
        Inquiry inquiry = Inquiry.builder()
                .propertyId(request.getPropertyId())
                .customerId(request.getCustomerId())
                .message(request.getMessage())
                .responded(false)
                .build();
                
        Inquiry savedInquiry = inquiryRepository.save(inquiry);
        return mapToResponse(savedInquiry);
    }

    @Override
    public List<InquiryResponse> getAllInquiries() {
        return inquiryRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InquiryResponse getInquiryById(String id) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found with id: " + id));
        return mapToResponse(inquiry);
    }

    @Override
    public List<InquiryResponse> getInquiriesByPropertyId(String propertyId) {
        return inquiryRepository.findByPropertyId(propertyId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private InquiryResponse mapToResponse(Inquiry inquiry) {
        return InquiryResponse.builder()
                .id(inquiry.getId())
                .propertyId(inquiry.getPropertyId())
                .customerId(inquiry.getCustomerId())
                .message(inquiry.getMessage())
                .responded(inquiry.isResponded())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }
}
