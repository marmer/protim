package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import lombok.var;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class UserDTOTest {
    @Test
    public void testSetRoles_NullGiven_RolseShouldBeSet()
            throws Exception {
        // Preparation
        final var classUnderTest = new UserDTO();

        // Execution
        classUnderTest.setRoles((Role[]) null);

        // Assertion
        assertThat(classUnderTest.getRoles(), is(nullValue()));
    }

    @Test
    public void test_RolesGiven_()
            throws Exception {
        // Preparation

        // Execution
        classUnderTest.();

        // Assertion
        fail("implement me!");
    }
}