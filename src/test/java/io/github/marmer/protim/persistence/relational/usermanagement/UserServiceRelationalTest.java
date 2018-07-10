package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;

public class UserServiceRelationalTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @InjectMocks
    private UserServiceRelational underTest;
    @Mock
    private Converter<User, UserDBO> userDBOConverter;
    @Mock
    private UserRepository userDBORepository;


    @Test
    public void testAddUser_UserDoesNotYetExist_ShouldCreateUser()
            throws Exception {
        // Preparation
        final User user = newUser().username("me").build();
        final UserDBO dbo = mock(UserDBO.class);
        when(userDBOConverter.convert(user)).thenReturn(dbo);
        when(userDBORepository.existsByUsername(user.getUsername())).thenReturn(false);

        // Execution
        underTest.addUser(user);

        // Assertion
        verify(userDBORepository).save(dbo);
    }

    @Test
    public void testAddUser_UserDoesAllreadyExist_ShouldNotCreateUser()
            throws Exception {
        // Preparation
        final User user = newUser().username("me").build();
        final UserDBO dbo = mock(UserDBO.class);
        when(userDBOConverter.convert(user)).thenReturn(dbo);
        when(userDBORepository.existsByUsername(user.getUsername())).thenReturn(true);

        // Execution
        underTest.addUser(user);

        // Assertion
        verifyNoMoreInteractions(userDBORepository);
    }

    private User.UserBuilder newUser() {
        return User.builder().username("someOne");
    }

}