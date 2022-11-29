package com.api.patientApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PatientApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientApiApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

	@GetMapping("/")
	public String index() {return  "It Works!";}
}
