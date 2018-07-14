package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.service.usermanagement.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.api.usermanagement.UserDTOMatcher.isUserDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class UserDTOConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private UserDTOConverter underTest;

    @Test
    public void testConvert_UserGiven_ShouldReturnConverted()
            throws Exception {
        // Preparation
        final User user = User.builder().build();

        // Execution
        final UserDTO result = underTest.convert(user);

        // Assertion
        assertThat(result, isUserDTO()
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .withRoles(user.getRoles())
                .withEnabled(user.isEnabled())
        );
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final UserDTO result = underTest.convert((User) null);

        // Assertion
        assertThat(result, is(nullValue()));
    }
}