package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.persistence.relational.usermanagement.RoleDBOMatcher.isRoleDBO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class RoleDBOConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private RoleDBOConverter underTest;

    @Test
    public void testConvert_RoleGiven_ShouldConvertedDBO()
            throws Exception {
        // Preparation
        final Role role = Role.USER;

        // Execution
        final RoleDBO result = underTest.convert(role);

        // Assertion
        assertThat(result, isRoleDBO().withName(role));
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final RoleDBO result = underTest.convert(null);

        // Assertion
        assertThat(result, is(nullValue()));
    }
}