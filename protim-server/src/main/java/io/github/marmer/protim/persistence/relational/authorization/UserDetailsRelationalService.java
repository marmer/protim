package io.github.marmer.protim.persistence.relational.authorization;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsRelationalService implements UserDetailsService {
    private final UserService userService;
    private final Converter<User, UserDetails> userDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return userService.getUser(username)
                .map(userDetailsConverter::convert)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
