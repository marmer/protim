package io.github.marmer.protim;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.marmer.protim.model.Useraccount;
import io.github.marmer.protim.repositories.UseraccountRepository;

@SpringBootApplication
public class ProtimApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtimApplication.class, args);

	}

	@Bean
	CommandLineRunner init(final UseraccountRepository accountRepository) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				String username = "protim";
				//TODO this should not happen
				if (accountRepository.findByUsername(username) == null)
					accountRepository.save(Useraccount.builder().username(username).password("protimpw").build());
			}

		};

	}
}
