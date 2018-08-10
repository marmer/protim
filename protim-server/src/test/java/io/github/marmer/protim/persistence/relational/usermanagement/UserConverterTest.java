package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.persistence.relational.usermanagement.RoleDBOTestdata.newRoleDBO;
import static io.github.marmer.protim.persistence.relational.usermanagement.UserDBOTestdata.newUserDBO;
import static io.github.marmer.protim.service.usermanagement.UserMatcher.isUser;
import static java.util.Collections.singleton;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class UserConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @InjectMocks
    private UserConverter underTest;
    @Mock
    private Converter<RoleDBO, Role> roleConverter;

    @Test
    public void testConvert_DboGiven_ShouldReturnConverted()
            throws Exception {
        // Preparation
        final RoleDBO roleDBO = newRoleDBO();
        final UserDBO dbo = newUserDBO().setRoles(singleton(roleDBO));
        final Role role = Role.ADMIN;
        when(roleConverter.convert(roleDBO)).thenReturn(role);

        // Execution
        final User converted = underTest.convert(dbo);

        // Assertion
        assertThat(converted, isUser()
                .withUsername(dbo.getUsername())
                .withPassword(dbo.getPassword())
                .withRoles(singleton(role))
                .withEnabled(dbo.isEnabled()));
    }

}