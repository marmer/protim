package io.github.marmer.protim.persistence.relational.authorization;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsRelationalService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private Converter<User, UserDetails> userDetailsConverter;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        // TODO: marmer 12.08.2018 Add some kind of default behavior if no admin exists yet
        return userService.getUser(username)
                .map(user -> userDetailsConverter.convert(user))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
