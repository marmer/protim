package io.github.marmer.protim.service.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.util.Set;

@Value
@Wither
@Builder
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private Set<Role> roles;
    private boolean enabled;
}
