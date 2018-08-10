package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import org.springframework.stereotype.Service;

@Service("converterUserDtoToUser")
public class UserConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(final UserDTO source) {
        return source == null
                ? null
                : User.builder()
                .username(source.getUsername())
                .password(source.getPassword())
                .roles(source.getRoles())
                .enabled(source.isEnabled())
                .build();
    }
}
