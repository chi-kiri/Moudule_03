package com.example.milkteaauth.config;

import com.example.milkteaauth.entity.User;
import com.example.milkteaauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfiguration {

    @Bean
    public ApplicationRunner initializeAdmin(
            UserRepository users,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.email:}") String email,
            @Value("${app.admin.password:}") String password,
            @Value("${app.admin.phone:}") String phone
    ) {
        return args -> {
            if (email.isBlank() && password.isBlank() && phone.isBlank()) {
                return;
            }
            if (email.isBlank() || password.isBlank() || phone.isBlank()) {
                throw new IllegalStateException(
                        "Cần cấu hình đủ APP_ADMIN_EMAIL, APP_ADMIN_PASSWORD và APP_ADMIN_PHONE"
                );
            }
            var existingUser = users.findByEmail(email);
            if (existingUser.isPresent()) {
                if (!"ROLE_ADMIN".equals(existingUser.get().getRole())) {
                    throw new IllegalStateException("Email cấu hình ADMIN đã thuộc tài khoản khác");
                }
                return;
            }

            users.save(User.builder()
                    .email(email)
                    .phone(phone)
                    .password(passwordEncoder.encode(password))
                    .role("ROLE_ADMIN")
                    .build());
        };
    }
}
