package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.exception.RessourceConflictException;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import io.github.marmer.protim.service.usermanagement.UserpasswordEncodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceRelational implements UserService {
    private final Converter<User, UserDBO> userDBOConverter;
    private final UserRepository userDBORepository;
    private final Converter<UserDBO, User> userConverter;
    private final UserpasswordEncodingService userpasswordEncodingService;

    @Override
    public void addUser(final User user) {
        if (userDBORepository.existsByUsername(user.getUsername())) {
            throw new RessourceConflictException("A user with username '" + user.getUsername() + "' exists allready.");
        }

        final User userWithEncodedPassword = userpasswordEncodingService.getWithEncodedPassword(user);
        final UserDBO dbo = userDBOConverter.convert(userWithEncodedPassword);
        userDBORepository.save(dbo);
    }

    @Override
    public Optional<User> getUser(final String username) {
        return userDBORepository.findByUsername(username).map(userConverter::convert);
    }

    @Override
    public List<String> getUsernames() {
        return userDBORepository.findAllUsernames();
    }


}
