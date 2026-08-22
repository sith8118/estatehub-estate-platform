package com.estatehub.property;

import com.estatehub.property.model.Property;
import com.estatehub.property.model.Property.PropertyType;
import com.estatehub.property.model.Property.PropertyStatus;
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
            if (repository.count() == 0) {
                for (int i = 1; i <= 5; i++) {
                    Property property = new Property();
                    property.setTitle("Luxury Property " + i);
                    property.setDescription("A beautiful and spacious property located in a prime area.");
                    property.setPrice(BigDecimal.valueOf(100000 * i));
                    property.setLocation("City Center " + i);
                    property.setPropertyType(PropertyType.VILLA);
                    property.setBedrooms(i + 1);
                    property.setBathrooms(i);
                    property.setArea(1500.0 + (i * 200));
                    property.setStatus(PropertyStatus.AVAILABLE);
                    property.setAgentId(String.valueOf(i));
                    property.setCreatedAt(LocalDateTime.now());
                    property.setUpdatedAt(LocalDateTime.now());
                    
                    repository.save(property);
                }
                System.out.println("Inserted 5 default properties into Property Database");
            }
        };
    }
}
