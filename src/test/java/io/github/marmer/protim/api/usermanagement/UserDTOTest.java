package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import lombok.var;
import org.junit.Test;

import static io.github.marmer.protim.api.usermanagement.UserDTOMatcher.isUserDTO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UserDTOTest {
    @Test
    public void testSetRoles_NullGiven_RolseShouldBeSet()
            throws Exception {
        // Preparation
        final var classUnderTest = new UserDTO();

        // Execution
        final UserDTO result = classUnderTest.setRoles((Role[]) null);

        // Assertion
        assertThat(result, isUserDTO().withRoles(is(nullValue())));
    }

    @Test
    public void testSetRoles_RolesGiven_RolesShouldBeSet()
            throws Exception {
        // Preparation
        final var classUnderTest = new UserDTO();

        // Execution
        final Role role1 = Role.ADMIN;
        final Role role2 = Role.USER;
        final UserDTO result = classUnderTest.setRoles(role1, role2);

        // Assertion
        assertThat(result, isUserDTO().withRoles(containsInAnyOrder(role1, role2)));
    }

    @Test
    public void testSetRoles_NothingGiven_ShouldSetEmpty()
            throws Exception {
        // Preparation
        final var classUnderTest = new UserDTO();

        // Execution
        final UserDTO result = classUnderTest.setRoles();

        // Assertion
        assertThat(result, isUserDTO().withRoles(is(empty())));
    }

}
