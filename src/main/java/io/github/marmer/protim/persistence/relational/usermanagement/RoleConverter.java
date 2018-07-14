package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.Converter;
import org.springframework.stereotype.Service;

@Service("converterRoleDBOToRole")
public class RoleConverter implements Converter<RoleDBO, Role> {
    @Override
    public Role convert(final RoleDBO source) {
        return source == null
                ? null
                : source.getName();
    }
}
