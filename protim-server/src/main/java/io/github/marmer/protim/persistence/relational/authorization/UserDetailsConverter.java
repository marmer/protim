package io.github.marmer.protim.persistence.relational.authorization;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toSet;

@Service
public class UserDetailsConverter implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(final User source) {
        return source == null
                ? null
                : new org.springframework.security.core.userdetails.User(
                source.getUsername(),
                source.getPassword(),
                source.isEnabled(),
                true,
                true,
                true,
                source.getRoles().stream()
                        .map(Role::name)
                        .map(this::withRolePraefix)
                        .map(SimpleGrantedAuthority::new).collect(toSet()));
    }

    private String withRolePraefix(final String name) {
        return "ROLE_" + name;
    }
}
