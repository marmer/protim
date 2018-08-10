package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service("converterUserDBOToUser")
@RequiredArgsConstructor
public class UserConverter implements Converter<UserDBO, User> {
    private final Converter<RoleDBO, Role> roleConverter;

    @Override
    public User convert(final UserDBO source) {
        return User.builder()
                .username(source.getUsername())
                .password(source.getPassword())
                .roles(source.getRoles().stream().map(roleConverter::convert).collect(Collectors.toSet()))
                .enabled(source.isEnabled())
                .build();
    }
}
