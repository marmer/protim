package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.service.usermanagement.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.api.usermanagement.UserDTOTestdata.newUserDTO;
import static io.github.marmer.protim.service.usermanagement.UserMatcher.isUser;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class UserConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private UserConverter underTest;

    @Test
    public void testConvert_DtoGiven_ShouldReturnMatchingModel()
            throws Exception {
        // Preparation
        final UserDTO userDTO = newUserDTO();

        // Execution
        final User result = underTest.convert(userDTO);

        // Assertion
        assertThat(result, isUser()
                .withUsername(result.getUsername())
                .withPassword(result.getPassword())
                .withRoles(result.getRoles())
                .withEnabled(result.isEnabled()));
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final User result = underTest.convert((UserDTO) null);

        // Assertion
        assertThat(result, is(nullValue()));
    }
}