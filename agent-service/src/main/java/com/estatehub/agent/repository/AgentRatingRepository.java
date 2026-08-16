package com.estatehub.agent.repository;

import com.estatehub.agent.model.AgentRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRatingRepository extends JpaRepository<AgentRating, Long> {
    List<AgentRating> findByAgentId(Long agentId);
}
