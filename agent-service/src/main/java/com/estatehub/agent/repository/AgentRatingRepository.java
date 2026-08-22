package com.estatehub.agent.repository;

import com.estatehub.agent.model.AgentRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRatingRepository extends MongoRepository<AgentRating, String> {
    List<AgentRating> findByAgentId(String agentId);
}
