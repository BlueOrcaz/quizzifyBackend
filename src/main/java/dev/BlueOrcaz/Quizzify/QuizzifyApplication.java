package dev.BlueOrcaz.Quizzify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class QuizzifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizzifyApplication.class, args);
	}

}
