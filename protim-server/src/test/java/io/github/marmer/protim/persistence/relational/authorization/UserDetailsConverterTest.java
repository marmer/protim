package io.github.marmer.protim.persistence.relational.authorization;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static io.github.marmer.protim.service.Converter.asSet;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UserDetailsConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private UserDetailsConverter underTest;

    @Test
    public void testConvert_UserGiven_ShouldReturnUserdetailsWithRelevantInformation()
            throws Exception {
        // Preparation
        final User user = User.builder()
                .username("DagobertD")
                .password("Quack")
                .enabled(true)
                .roles(asSet(Role.ADMIN, Role.USER)).build();

        // Execution
        final UserDetails result = underTest.convert(user);
        // Assertion
        assertThat(result, isUserDetails()
                .with("username", is(user.getUsername()))
                .with("password", is(user.getPassword()))
                .with("authorities", containsInAnyOrder(
                        isGrantedAuthority()
                                .with("authority", equalTo("ROLE_USER")),
                        isGrantedAuthority()
                                .with("authority", equalTo("ROLE_ADMIN"))))
                .with("enabled", is(user.isEnabled()))
                .with("accountNonExpired", is(true))
                .with("accountNonLocked", is(true))
                .with("credentialsNonExpired", is(true)));
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final UserDetails result = underTest.convert((User) null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

    private BeanPropertyMatcher<GrantedAuthority> isGrantedAuthority() {
        return new BeanPropertyMatcher<>(GrantedAuthority.class);
    }

    private BeanPropertyMatcher<UserDetails> isUserDetails() {
        return new BeanPropertyMatcher<>(UserDetails.class);
    }

}