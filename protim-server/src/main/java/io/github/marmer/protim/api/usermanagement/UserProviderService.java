package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserProviderService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        switch (username) {
            case "user":
                return new User(username,
                        passwordEncoder.encode("user123"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_" + Role.USER)));
            case "admin":
                return new User(username,
                        passwordEncoder.encode("admin123"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_" + Role.ADMIN)));
            default:
                throw new UsernameNotFoundException(username);
        }
    }
}
