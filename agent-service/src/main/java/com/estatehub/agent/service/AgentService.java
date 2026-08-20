package com.estatehub.agent.service;

import com.estatehub.agent.dto.AgentRequest;
import com.estatehub.agent.dto.AgentResponse;
import com.estatehub.agent.dto.RatingRequest;
import com.estatehub.agent.model.AgentRating;

import java.util.List;

public interface AgentService {
    
    AgentResponse registerAgent(AgentRequest request);
    
    List<AgentResponse> getAllAgents();
    
    AgentResponse getAgentById(String id);
    
    AgentResponse updateAgent(String id, AgentRequest request);
    
    void deleteAgent(String id);
    
    AgentResponse addRating(String agentId, RatingRequest request);
    
    List<AgentRating> getAgentRatings(String agentId);
}
