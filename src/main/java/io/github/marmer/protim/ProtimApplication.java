package io.github.marmer.protim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProtimApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtimApplication.class, args);
	}
}
