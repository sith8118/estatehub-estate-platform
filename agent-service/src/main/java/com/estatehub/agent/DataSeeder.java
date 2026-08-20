package com.estatehub.agent;

import com.estatehub.agent.model.Agent;
import com.estatehub.agent.repository.AgentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(AgentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                for (int i = 1; i <= 5; i++) {
                    Agent agent = new Agent();
                    agent.setName("Agent Member " + i);
                    agent.setEmail("agent" + i + "@estatehub.com");
                    agent.setPhone("+123456789" + i);
                    agent.setLicenseNumber("LIC-100" + i);
                    agent.setExperienceYears(i + 2);
                    agent.setRating(4.0 + (i * 0.1));
                    agent.setCreatedAt(LocalDateTime.now());
                    agent.setUpdatedAt(LocalDateTime.now());
                    
                    repository.save(agent);
                }
                System.out.println("Inserted 5 default agents into Agent Database");
            }
        };
    }
}
