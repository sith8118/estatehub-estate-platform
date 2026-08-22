package com.estatehub.agent.repository;

import com.estatehub.agent.model.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends MongoRepository<Agent, String> {
    Optional<Agent> findByEmail(String email);
    Optional<Agent> findByLicenseNumber(String licenseNumber);
}
