package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.exception.RessourceConflictException;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserpasswordEncodingService;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceRelationalTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @InjectMocks
    private UserServiceRelational underTest;
    @Mock
    private Converter<User, UserDBO> userDBOConverter;
    @Mock
    private UserRepository userDBORepository;
    @Mock
    private Converter<UserDBO, User> userConverter;
    @Mock
    private UserpasswordEncodingService userpasswordEncodingService;
    @Before
    public void setUp() throws Exception {
        underTest = new UserServiceRelational(userDBOConverter, userDBORepository, userConverter, userpasswordEncodingService);
    }

    @Test
    public void testGetUsernames_UsersExist_ShouldReturnUsernames()
            throws Exception {
        // Preparation
        final List<String> usernames = List.of("Big123", "Small321");
        when(userDBORepository.findAllUsernames()).thenReturn(usernames);

        // Execution
        final List<String> result = underTest.getUsernames();

        // Assertion
        assertThat(result, containsInAnyOrder("Big123", "Small321"));
    }

    @Test
    public void testAddUser_UserDoesNotYetExist_ShouldCreateUser()
            throws Exception {
        // Preparation
        final User user = newUser().username("me").build();
        final User userWithEncodedPassword = newUser().username("encodedMe").build();

        final UserDBO dbo = mock(UserDBO.class);
        when(userpasswordEncodingService.getWithEncodedPassword(user)).thenReturn(userWithEncodedPassword);
        when(userDBOConverter.convert(userWithEncodedPassword)).thenReturn(dbo);
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
        when(userDBORepository.existsByUsername(user.getUsername())).thenReturn(true);

        // Execution
        try {
            underTest.addUser(user);
        } catch (final RessourceConflictException e) {
            //expected
        }

        // Assertion
        verifyNoMoreInteractions(userDBORepository);
    }

    @Test
    public void testAddUser_UserDoesAllreadyExist_ShouldThrowApproptiateException()
            throws Exception {
        // Preparation
        final User user = newUser().username("me").build();
        final UserDBO dbo = mock(UserDBO.class);
        when(userDBOConverter.convert(user)).thenReturn(dbo);
        when(userDBORepository.existsByUsername(user.getUsername())).thenReturn(true);
        // Assertion
        exception.expect(RessourceConflictException.class);
        exception.expectMessage(is("A user with username '" + user.getUsername() + "' exists allready."));
        // Execution
        underTest.addUser(user);
    }


    @Test
    public void testGetUser_UserExists_ShouldReturnUser()
            throws Exception {
        // Preparation
        final UserDBO userDBO = mock(UserDBO.class);
        final String username = "me";
        final User user = newUser().username(username).build();
        when(userDBORepository.findByUsername(username)).thenReturn(Optional.of(userDBO));
        when(userConverter.convert(userDBO)).thenReturn(user);

        // Execution
        final Optional<User> result = underTest.getUser(username);

        // Assertion
        assertThat(result.get(), is(user));
    }

    @Test
    public void testGetUser_UserDoesNotExist_ShouldReturnNoUser()
            throws Exception {
        // Preparation
        final String username = "me";
        when(userDBORepository.findByUsername(username)).thenReturn(Optional.empty());

        // Execution
        final Optional<User> result = underTest.getUser(username);

        // Assertion
        assertThat(result, is(not(present())));
    }

    private <T> Matcher<Optional<T>> present() {
        return new TypeSafeMatcher<Optional<T>>() {
            @Override
            protected boolean matchesSafely(final Optional<T> item) {
                return item.isPresent();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("value present");
            }

            @Override
            protected void describeMismatchSafely(final Optional<T> item, final Description mismatchDescription) {
                if (item == null) {
                    mismatchDescription.appendText("null");
                } else {
                    mismatchDescription.appendText("no value present");
                }
            }
        };
    }

    private User.UserBuilder newUser() {
        return User.builder().username("someOne");
    }

}