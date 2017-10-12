package io.github.marmer.protim;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.marmer.protim.model.Useraccount;
import io.github.marmer.protim.repositories.UseraccountRepository;

@SpringBootApplication
public class ProtimApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ProtimApplication.class, args);

	}

	// TODO Refresh Javascript skills
	// TODO In React einlesen https://reactjs.org/tutorial/tutorial.html
	// TODO Automatic creation of docker images (and Pushing to docker registry)
	// TODO UI Tests (Javascript as well as and the whole site)
	// TODO encrypt rest communication (ssl)
	// TODO tests for security
	// TODO store passwords hashed with salts
	// TODO User-Management (creation and management)

	@Bean
	CommandLineRunner init(final UseraccountRepository accountRepository) {

		return new CommandLineRunner() {

			@Override
			public void run(final String... arg0) throws Exception {
				final String username = "protim";
				// TODO this should not happen
				if (accountRepository.findByUsername(username) == null) {
					accountRepository.save(Useraccount.builder().username(username).password("protimpw").build());
				}
			}

		};

	}
}
