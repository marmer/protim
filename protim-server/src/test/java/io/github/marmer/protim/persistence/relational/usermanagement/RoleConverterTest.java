package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.persistence.relational.usermanagement.RoleDBOTestdata.newRoleDBO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class RoleConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private RoleConverter underTest;

    @Test
    public void testConvert_DboGiven_ShouldConvertToRole()
            throws Exception {
        // Preparation
        final RoleDBO dbo = newRoleDBO();

        // Execution
        final Role result = underTest.convert(dbo);

        // Assertion
        assertThat(result, is(dbo.getName()));
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final Role result = underTest.convert((RoleDBO) null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

}