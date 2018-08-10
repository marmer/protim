package io.github.marmer.protim.service.usermanagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * With BCrypt encoding and prefix.
 */
@Service
public class UserpasswordEncodingBCryptWithPrefixService implements UserpasswordEncodingService {
    @Override
    public User getWithEncodedPassword(final User user) {
        return user == null
                ? null
                : user.withPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(user.getPassword()));
    }

}
