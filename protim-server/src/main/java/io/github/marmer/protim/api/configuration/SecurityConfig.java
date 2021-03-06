package io.github.marmer.protim.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest.toAnyEndpoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable() // we are stateles (no session or cookies) so it should be fine
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .logout().deleteCookies("*").invalidateHttpSession(false).clearAuthentication(true)
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll().and()
                .authorizeRequests()
                .requestMatchers(toAnyEndpoint()).hasRole(Role.ADMIN.name())
                .antMatchers("/api/v*/usermanagement/**").hasRole(Role.ADMIN.name())
                .antMatchers("/api/v*/day/**").hasRole(Role.USER.name())
                .antMatchers("/*").permitAll()
                .antMatchers("/**").denyAll().and()
                .httpBasic().realmName("protim");
    }


    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        final String idForEncode = "bcrypt";
        final Map<String, PasswordEncoder> encoders = new HashMap<>();
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        encoders.put(idForEncode, bCryptPasswordEncoder);
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        final DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);
        return delegatingPasswordEncoder;
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
}
