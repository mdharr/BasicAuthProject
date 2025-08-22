package com.example.authdemo;

import com.example.authdemo.entity.User;
import com.example.authdemo.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableCaching
@SpringBootApplication
public class AuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthDemoApplication.class, args);
	}

//	@Bean
//	public ApplicationRunner adminUserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		return args -> {
//			if (userRepository.findByUsername("Admin").isEmpty()) {
//				System.out.println("No admin user found. Creating a default admin user.");
//
//				User adminUser = new User();
//				adminUser.setUsername("Admin");
//				adminUser.setPassword(passwordEncoder.encode("wombat1"));
//				adminUser.setEmail("admin@example.com");
//
//				userRepository.save(adminUser);
//				System.out.println("Admin user created successfully!");
//			} else {
//				System.out.println("Admin user already exists. Skipping creation.");
//			}
//		};
//	}
}
