package com.api.patientApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PatientApiApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(PatientApiApplication.class, args);
	}

}
