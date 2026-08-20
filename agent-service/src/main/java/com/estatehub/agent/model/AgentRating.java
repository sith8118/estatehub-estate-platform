package com.estatehub.agent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "agent_ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentRating {

    @Id
    private String id;

    private String agentId;

    private String customerId;

    private Integer score;

    private String review;

    private LocalDateTime createdAt;
    
    
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
