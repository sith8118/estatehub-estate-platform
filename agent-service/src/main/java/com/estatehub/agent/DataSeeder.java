package com.estatehub.agent;

import com.estatehub.agent.model.Agent;
import com.estatehub.agent.repository.AgentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(AgentRepository repository) {
        return args -> {
            if (!repository.existsById("1")) {
                Agent entity = new Agent();
                entity.setId("1");
                repository.save(entity);
                System.out.println("Inserted default Agent with ID 1");
            }
        };
    }
}
