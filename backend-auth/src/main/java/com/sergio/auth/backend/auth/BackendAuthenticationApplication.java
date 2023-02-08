package com.sergio.auth.backend.auth;

import com.sergio.auth.backend.auth.config.PasswordConfig;
import com.sergio.auth.backend.auth.entities.AuthUser;
import com.sergio.auth.backend.auth.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendAuthenticationApplication {

	public BackendAuthenticationApplication(UserRepository userRepository, PasswordConfig passwordEncoder) {
		this.userRepository = userRepository;

		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendAuthenticationApplication.class, args);
	}
	private final UserRepository userRepository;
	private final PasswordConfig passwordEncoder;

//	@Bean
	CommandLineRunner runner(){
		return args -> {
			AuthUser user = new AuthUser(1L,"admin",passwordEncoder.passwordEncoder().encode("admin"),"admin");
			userRepository.save(user);
		};
	}
}
