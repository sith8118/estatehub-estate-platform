import os

services = {
    'property-service': ('Property', 'com.estatehub.property.model.Property', 'com.estatehub.property.repository.PropertyRepository'),
    'agent-service': ('Agent', 'com.estatehub.agent.model.Agent', 'com.estatehub.agent.repository.AgentRepository'),
    'booking-service': ('Booking', 'com.estatehub.booking.model.Booking', 'com.estatehub.booking.repository.BookingRepository'),
    'payment-service': ('Payment', 'com.estatehub.payment.model.Payment', 'com.estatehub.payment.repository.PaymentRepository')
}

for svc, (entity, model_pkg, repo_pkg) in services.items():
    pkg = svc.split('-')[0]
    seeder_code = f'''package com.estatehub.{pkg};

import {model_pkg};
import {repo_pkg};
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {{

    @Bean
    public CommandLineRunner initData({entity}Repository repository) {{
        return args -> {{
            if (!repository.existsById("1")) {{
                {entity} entity = new {entity}();
                entity.setId("1");
                repository.save(entity);
                System.out.println("Inserted default {entity} with ID 1");
            }}
        }};
    }}
}}
'''
    path = os.path.join(svc, 'src/main/java/com/estatehub', pkg, 'DataSeeder.java')
    with open(path, 'w') as f:
        f.write(seeder_code)

