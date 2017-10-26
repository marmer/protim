package io.github.marmer.protim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class HttpBasicSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");

		// .passwordEncoder(new ShaPasswordEncoder(256));

		// DaoAuthenticationProvider authenticationProvider = new
		// DaoAuthenticationProvider();
		// ReflectionSaltSource rss = new ReflectionSaltSource();
		// rss.setUserPropertyToUse("salt");
		// DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		// provider.setSaltSource(rss);
		// provider.setUserDetailsService(authService);
		// provider.setPasswordEncoder(new MyPasswordEncoder());
		// auth.authenticationProvider(provider);

	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// TODO turn csrf security on again and get out how to use id with a rest client
		// TODO turn csrf security on again and get out how to use id with angular
		// new HttpSessionCsrfTokenRepository();

		// TODO switch from basic auth to Digest Access Authentication with a secure
		// enough hash algorithm (maybe sha256?)
		http.csrf().disable()
				/* .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and() */.authorizeRequests()
				.antMatchers("/rest/**").hasRole("ADMIN").and().httpBasic().and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("protim").permitAll();

	}
}
