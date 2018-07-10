package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

@Data
@Accessors(chain = true)
public class UserDTO {
    private String username;
    private String password;
    private Set<Role> roles;
    private boolean enabled;

    public UserDTO setRoles(final Role... roles) {
        setRoles(roles == null ? null : new HashSet<>(asList(roles)));
        return this;
    }
}
