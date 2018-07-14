package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("converterUserToUserDBO")
@RequiredArgsConstructor
public class UserDBOConverter implements Converter<User, UserDBO> {
    private final Converter<Role, RoleDBO> roleDBOConverter;

    @Override
    public UserDBO convert(final User source) {
        return source == null ? null : new UserDBO()
                .setUsername(source.getUsername())
                .setPassword(source.getPassword())
                .setRoles(roleDBOConverter.convert(source.getRoles()))
                .setEnabled(source.isEnabled());
    }


}
