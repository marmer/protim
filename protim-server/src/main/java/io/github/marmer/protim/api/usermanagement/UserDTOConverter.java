package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import org.springframework.stereotype.Service;

@Service
public class UserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(final User source) {
        return source == null
                ? null
                : new UserDTO()
                .setUsername(source.getUsername())
                .setPassword(source.getPassword())
                .setRoles(source.getRoles())
                .setEnabled(source.isEnabled())
                .setRoles(source.getRoles());
    }
}
