package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceRelational implements UserService {
    private final Converter<User, UserDBO> userDBOConverter;
    private final UserRepository userDBORepository;

    @Override
    public void addUser(final User user) {
        final UserDBO dbo = userDBOConverter.convert(user);
        if (!userDBORepository.existsByUsername(user.getUsername())) {
            userDBORepository.save(dbo);
        }
    }
}
