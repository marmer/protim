package io.github.marmer.protim.service.usermanagement;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static io.github.marmer.protim.service.usermanagement.UserMatcher.isUser;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class UserpasswordEncodingBCryptWithPrefixServiceTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @InjectMocks
    private UserpasswordEncodingBCryptWithPrefixService underTest;

    @Test
    public void testGetWithEncodedPassword_UserWithCleartextPasswordGiven_ShouldEncodePassword()
            throws Exception {
        // Preparation
        final String cleartextPassword = "cleartextPw";
        final User user = User.builder().password(cleartextPassword).build();

        // Execution
        final User result = underTest.getWithEncodedPassword(user);

        // Assertion
        assertThat(result, isUser().withPassword(matchesBCryptEncodedAndPraefixedPassword(cleartextPassword)));
    }

    @Test
    public void testGetWithEncodedPassword_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final User result = underTest.getWithEncodedPassword(null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

    private Matcher<String> matchesBCryptEncodedAndPraefixedPassword(final String password) {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(final String encodedPassword) {
                if (!isPrefixed(encodedPassword)) {
                    return false;
                } else {
                    return new BCryptPasswordEncoder().matches(password, encodedPassword.replaceFirst("\\{bcrypt}", ""));
                }
            }

            private boolean isPrefixed(final String encodedPassword) {
                return encodedPassword.startsWith("{bcrypt}");
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("A BCrypt encrypted Version of ").appendValue(password);
            }
        };
    }
}