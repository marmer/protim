package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.marmer.protim.test.DbCleanupService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.List;

import static io.github.marmer.protim.persistence.relational.usermanagement.UserDBOTestdata.newUserDBO;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@DataJpaTest
@Import(DbCleanupService.class)
public class UserRepositoryIT {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private UserRepository underTest;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private DbCleanupService dbCleanupService;

    @Before
    public void setUp() throws Exception {
        dbCleanupService.clearAll();
    }

    @Test
    public void testFindAllUsernames_EntitiesExist_ShouldReturnTheirUsernames()
            throws Exception {
        // Preparation
        entityManager.persistAndFlush(newUserDBO().setUsername("UserZ"));
        entityManager.persistAndFlush(newUserDBO().setUsername("UserX"));
        entityManager.persistAndFlush(newUserDBO().setUsername("UserY"));

        // Execution
        final List<String> results = underTest.findAllUsernames();

        // Assertion
        assertThat(results, contains("UserX", "UserY", "UserZ"));
    }

}