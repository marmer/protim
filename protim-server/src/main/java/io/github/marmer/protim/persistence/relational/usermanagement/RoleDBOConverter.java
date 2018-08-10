package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.Converter;
import org.springframework.stereotype.Service;

@Service("converterRoleToRoleDBO")
public class RoleDBOConverter implements Converter<Role, RoleDBO> {
    @Override
    public RoleDBO convert(final Role source) {
        return source == null ? null : new RoleDBO().setName(source);
    }
}
