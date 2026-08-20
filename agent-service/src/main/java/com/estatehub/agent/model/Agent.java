package com.estatehub.agent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent {

    @Id
    private String id;

    private String name;

    private String email;

    private String phone;

    private String licenseNumber;

    private Integer experienceYears;

    private Double rating;

    private String profileImageUrl;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    
    protected void onCreate() {
        this.rating = 0.0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
