package com.estatehub.booking;

import com.estatehub.booking.model.Booking;
import com.estatehub.booking.repository.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(BookingRepository repository) {
        return args -> {
            if (!repository.existsById("1")) {
                Booking entity = new Booking();
                entity.setId("1");
                repository.save(entity);
                System.out.println("Inserted default Booking with ID 1");
            }
        };
    }
}
