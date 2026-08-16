package com.estatehub.agent.service;

import com.estatehub.agent.dto.AgentRequest;
import com.estatehub.agent.dto.AgentResponse;
import com.estatehub.agent.dto.RatingRequest;
import com.estatehub.agent.model.Agent;
import com.estatehub.agent.model.AgentRating;
import com.estatehub.agent.repository.AgentRatingRepository;
import com.estatehub.agent.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final AgentRatingRepository agentRatingRepository;

    @Override
    public AgentResponse registerAgent(AgentRequest request) {
        if (agentRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already properly registered");
        }
        if (agentRepository.findByLicenseNumber(request.getLicenseNumber()).isPresent()) {
            throw new RuntimeException("License Number is already registered");
        }

        Agent agent = Agent.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .licenseNumber(request.getLicenseNumber())
                .experienceYears(request.getExperienceYears())
                .profileImageUrl(request.getProfileImageUrl())
                .build();
                
        Agent savedAgent = agentRepository.save(agent);
        return mapToResponse(savedAgent);
    }

    @Override
    public List<AgentResponse> getAllAgents() {
        return agentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AgentResponse getAgentById(Long id) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
        return mapToResponse(agent);
    }

    @Override
    public AgentResponse updateAgent(Long id, AgentRequest request) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
                
        agent.setName(request.getName());
        agent.setPhone(request.getPhone());
        agent.setExperienceYears(request.getExperienceYears());
        agent.setProfileImageUrl(request.getProfileImageUrl());
        
        Agent updatedAgent = agentRepository.save(agent);
        return mapToResponse(updatedAgent);
    }

    @Override
    public void deleteAgent(Long id) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
        agentRepository.delete(agent);
    }

    @Override
    @Transactional
    public AgentResponse addRating(Long agentId, RatingRequest request) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + agentId));
                
        AgentRating rating = AgentRating.builder()
                .agentId(agentId)
                .customerId(request.getCustomerId())
                .score(request.getScore())
                .review(request.getReview())
                .build();
                
        agentRatingRepository.save(rating);
        
        // Recalculate average rating
        List<AgentRating> allRatings = agentRatingRepository.findByAgentId(agentId);
        double averageRating = allRatings.stream()
                .mapToInt(AgentRating::getScore)
                .average()
                .orElse(0.0);
                
        agent.setRating(averageRating);
        Agent updatedAgent = agentRepository.save(agent);
        
        return mapToResponse(updatedAgent);
    }

    @Override
    public List<AgentRating> getAgentRatings(Long agentId) {
        if (!agentRepository.existsById(agentId)) {
             throw new RuntimeException("Agent not found with id: " + agentId);
        }
        return agentRatingRepository.findByAgentId(agentId);
    }

    private AgentResponse mapToResponse(Agent agent) {
        return AgentResponse.builder()
                .id(agent.getId())
                .name(agent.getName())
                .email(agent.getEmail())
                .phone(agent.getPhone())
                .licenseNumber(agent.getLicenseNumber())
                .experienceYears(agent.getExperienceYears())
                .rating(agent.getRating())
                .profileImageUrl(agent.getProfileImageUrl())
                .createdAt(agent.getCreatedAt())
                .updatedAt(agent.getUpdatedAt())
                .build();
    }
}
