package com.estatehub.auth;

import com.estatehub.auth.model.User;
import com.estatehub.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {
    
    @Bean
    public CommandLineRunner initData(UserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() == 0) {
                for (int i = 1; i <= 5; i++) {
                    User user = new User();
                    user.setName("Group Member " + i);
                    user.setEmail("member" + i + "@gmail.com");
                    user.setPassword(passwordEncoder.encode("password123"));
                    user.setRole("CUSTOMER");
                    repository.save(user);
                }
                System.out.println("Inserted 5 default users into Auth Database");
            }
        };
    }
}
