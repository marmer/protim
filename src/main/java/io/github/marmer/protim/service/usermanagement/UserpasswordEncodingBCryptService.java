package io.github.marmer.protim.service.usermanagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserpasswordEncodingBCryptService implements UserpasswordEncodingService {
    @Override
    public User getWithEncodedPassword(final User user) {

        return user == null ? null : user.withPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    }

}
