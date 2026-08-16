package com.estatehub.agent.service;

import com.estatehub.agent.dto.AgentRequest;
import com.estatehub.agent.dto.AgentResponse;
import com.estatehub.agent.dto.RatingRequest;
import com.estatehub.agent.model.AgentRating;

import java.util.List;

public interface AgentService {
    
    AgentResponse registerAgent(AgentRequest request);
    
    List<AgentResponse> getAllAgents();
    
    AgentResponse getAgentById(Long id);
    
    AgentResponse updateAgent(Long id, AgentRequest request);
    
    void deleteAgent(Long id);
    
    AgentResponse addRating(Long agentId, RatingRequest request);
    
    List<AgentRating> getAgentRatings(Long agentId);
}
