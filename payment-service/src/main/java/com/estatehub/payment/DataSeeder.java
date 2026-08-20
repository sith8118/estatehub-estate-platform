package com.estatehub.payment;

import com.estatehub.payment.model.Payment;
import com.estatehub.payment.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(PaymentRepository repository) {
        return args -> {
            if (!repository.existsById("1")) {
                Payment entity = new Payment();
                entity.setId("1");
                repository.save(entity);
                System.out.println("Inserted default Payment with ID 1");
            }
        };
    }
}
