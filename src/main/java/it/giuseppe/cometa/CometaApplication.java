package it.giuseppe.cometa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CometaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CometaApplication.class, args);
	}

}
