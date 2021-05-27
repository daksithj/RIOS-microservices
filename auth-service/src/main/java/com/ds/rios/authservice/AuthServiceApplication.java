package com.ds.rios.authservice;

import com.ds.rios.authservice.Models.AppUser;
import com.ds.rios.authservice.Models.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication {

	@Autowired
	private BCryptPasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AppUserRepository repository) {
		return (args) -> {

			repository.save(new AppUser("warehouse", encoder.encode("12345"), "WAREHOUSE"));
			repository.save(new AppUser("delivery", encoder.encode("12345"), "DELIVERY"));
			repository.save(new AppUser("retail", encoder.encode("12345"), "RETAIL"));

		};
	}
}
