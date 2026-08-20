package com.estatehub.property;

import com.estatehub.property.model.Property;
import com.estatehub.property.repository.PropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(PropertyRepository repository) {
        return args -> {
            if (!repository.existsById("1")) {
                Property entity = new Property();
                entity.setId("1");
                repository.save(entity);
                System.out.println("Inserted default Property with ID 1");
            }
        };
    }
}
