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

import static io.github.marmer.protim.persistence.relational.usermanagement.UserDBOMatcher.isUserDBO;
import static io.github.marmer.protim.persistence.relational.usermanagement.UserTestdata.newUser;
import static java.util.Collections.singleton;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDBOConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private UserDBOConverter underTest;
    @Mock
    private Converter<Role, RoleDBO> roleDBOConverter;

    @Test
    public void testConvert_ModelGiven_ShouldReturnConvertedDBO()
            throws Exception {
        // Preparation
        final Role role = Role.USER;
        final User user = newUser().withRoles(singleton(role));
        final RoleDBO roles = mock(RoleDBO.class);
        when(roleDBOConverter.convert(role)).thenReturn(roles);

        // Execution
        final UserDBO result = underTest.convert(user);

        // Assertion
        assertThat(result, isUserDBO()
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .withRoles(contains(roles))
                .withEnabled(user.isEnabled()));
    }

    @Test
    public void testConvert_ModelWithoutRolesGiven_ModelRolesShouldBeNull()
            throws Exception {
        // Preparation
        final User user = newUser().withRoles(null);
        final RoleDBO roles = mock(RoleDBO.class);

        // Execution
        final UserDBO result = underTest.convert(user);

        // Assertion
        assertThat(result, isUserDBO()
                .withRoles(is(nullValue())));
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final UserDBO result = underTest.convert(null);

        // Assertion
        assertThat(result, is(nullValue()));
    }
}