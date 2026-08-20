package com.estatehub.booking;

import com.estatehub.booking.model.Booking;
import com.estatehub.booking.model.Booking.BookingStatus;
import com.estatehub.booking.repository.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(BookingRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                for (int i = 1; i <= 5; i++) {
                    Booking booking = new Booking();
                    // Assuming property IDs and customer IDs generated previously could match somewhat
                    booking.setPropertyId(String.valueOf(i));
                    booking.setCustomerId("customer-id-" + i);
                    booking.setVisitDate(LocalDateTime.now().plusDays(i));
                    booking.setStatus(BookingStatus.CONFIRMED);
                    booking.setRemarks("Looking forward to visit " + i);
                    booking.setCreatedAt(LocalDateTime.now());
                    booking.setUpdatedAt(LocalDateTime.now());
                    
                    repository.save(booking);
                }
                System.out.println("Inserted 5 default bookings into Booking Database");
            }
        };
    }
}
