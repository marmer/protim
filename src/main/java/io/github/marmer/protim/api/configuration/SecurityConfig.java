package io.github.marmer.protim.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable() // we are stateles (no session or cookies) so it should be fine
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").hasRole("USER")
                .and()
                .httpBasic();
    }

    @Bean
    protected GlobalCorsConfig corsConfig() {
        return new GlobalCorsConfig();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource(final GlobalCorsConfig globalCorsConfig) {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(globalCorsConfig.getAllowedOrigins());
        configuration.setAllowedMethods(globalCorsConfig.getAllowedMethods());
        configuration.setAllowedHeaders(globalCorsConfig.getAllowedHeaders());
        configuration.setAllowCredentials(globalCorsConfig.getAllowCredentials());
        configuration.setExposedHeaders(globalCorsConfig.getExposedHeaders());
        configuration.setMaxAge(globalCorsConfig.getMaxAge());
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}
