package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.exception.RessourceConflictException;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceRelational implements UserService {
    private final Converter<User, UserDBO> userDBOConverter;
    private final UserRepository userDBORepository;
    private final Converter<UserDBO, User> userConverter;

    @Override
    public void addUser(final User user) {
        final UserDBO dbo = userDBOConverter.convert(user);
        if (userDBORepository.existsByUsername(user.getUsername())) {
            throw new RessourceConflictException("A user with username '" + user.getUsername() + "' exists allready.");
        } else {
            userDBORepository.save(dbo);
        }
    }

    @Override
    public Optional<User> getUser(final String username) {
        return userDBORepository.findByUsername(username).map(userConverter::convert);
    }


}
