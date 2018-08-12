package io.github.marmer.protim.persistence.relational.authorization;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailsRelationalServiceTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @InjectMocks
    private UserDetailsRelationalService underTest;
    @Mock
    private UserService userService;
    @Mock
    private Converter<User, UserDetails> userDetailsConverter;

    @Test
    public void testLoadUserByUsername_UserExists_ShouldServeUser()
            throws Exception {
        // Preparation
        final String username = "Frankenstein's Monster";
        final User user = newUser().withUsername(username);
        when(userService.getUser(username)).thenReturn(Optional.of(user));
        final UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsConverter.convert(user)).thenReturn(userDetails);

        // Execution
        final UserDetails result = underTest.loadUserByUsername(username);

        // Assertion
        assertThat(result, is(userDetails));
    }

    @Test
    public void testLoadUserByUsername_DoesNotExist_ShouldThrowException()
            throws Exception {
        // Preparation
        final String username = "Frankenstein's Monster";
        final User user = newUser().withUsername(username);
        when(userService.getUser(username)).thenReturn(Optional.empty());

        // Assertion
        exception.expect(UsernameNotFoundException.class);
        exception.expectMessage(containsString(username));

        // Execution
        underTest.loadUserByUsername(username);
    }

    private User newUser() {
        return User.builder().build();
    }
}