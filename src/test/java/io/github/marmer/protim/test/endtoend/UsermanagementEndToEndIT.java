package io.github.marmer.protim.test.endtoend;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.persistence.relational.usermanagement.RoleDBO;
import io.github.marmer.protim.persistence.relational.usermanagement.UserDBO;
import io.github.marmer.protim.test.DbCleanupService;
import io.github.marmer.protim.test.TransactionlessTestEntityManager;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.marmer.protim.persistence.relational.usermanagement.RoleDBOMatcher.isRoleDBO;
import static io.github.marmer.protim.persistence.relational.usermanagement.UserDBOMatcher.isUserDBO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class UsermanagementEndToEndIT {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DbCleanupService dbCleanupService;
    @Autowired
    private TransactionlessTestEntityManager entityManager;

    @Before
    public void setUp() {
        dbCleanupService.clearAll();
    }

    @Test
    public void testPutUser_UserDoesNotExistYet_UserShouldExistNow()
            throws Exception {
        // Preparation
        final String username = "Jim";

        // Execution
        mockMvc.perform(
                put("/api/v1/usermanagement/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"username\": \"Jim\",\n" +
                                "  \"password\": \"JTKirk\",\n" +
                                "  \"roles\": [\n" +
                                "    \"ADMIN\",\n" +
                                "    \"USER\"\n" +
                                "  ],\n" +
                                "  \"enabled\": true\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/usermanagement/users/Jim"));

        // Expectation
        assertThat(entityManager.findAllOf(UserDBO.class), contains(isUserDBO()
                .withId(is(notNullValue()))
                .withUsername(username)
                .withPassword("JTKirk")
                .withRoles(containsInAnyOrder(
                        isRoleDBO()
                                .withName(Role.ADMIN),
                        isRoleDBO()
                                .withName(Role.USER)))
                .withEnabled(true)
                .withVersion(is(notNullValue()))
        ));

    }

    @Test
    public void testPutUser_UserDoesAllreadyExist_UserShouldExistNow()
            throws Exception {
        // Preparation
        final String username = "Jim";
        final String oldUserPassword = "oldUserPassword";
        final Long oldUserId = entityManager.persistAndGetId(new UserDBO().setUsername(username).setPassword(oldUserPassword), Long.class);


        // Execution
        mockMvc.perform(
                put("/api/v1/usermanagement/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"username\": \"Jim\",\n" +
                                "  \"password\": \"JTKirk\",\n" +
                                "  \"roles\": [\n" +
                                "    \"ADMIN\",\n" +
                                "    \"USER\"\n" +
                                "  ],\n" +
                                "  \"enabled\": true\n" +
                                "}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorMsg", containsString(username)));

        // Expectation
        assertThat(entityManager.findAllOf(UserDBO.class),
                contains(isUserDBO()
                        .withId(oldUserId)
                        .withUsername(username)
                        .withPassword(oldUserPassword)
                ));
    }

    @Test
    public void testGetUser_UserExists_ShouldServeUser()
            throws Exception {
        // Preparation
        entityManager.persist(new UserDBO()
                .setUsername("Jack")
                .setPassword("O'Neill")
                .setRoles(asSet(Role.USER))
                .setEnabled(true)
        );

        // Execution
        mockMvc.perform(get("/api/v1/usermanagement/users/Jack"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"username\": \"Jack\",\n" +
                        "  \"password\": \"O'Neill\",\n" +
                        "  \"roles\": [\n" +
                        "    \"USER\"\n" +
                        "  ],\n" +
                        "  \"enabled\": true\n" +
                        "}"));
    }

    private <T> Set<RoleDBO> asSet(final Role... roles) {
        return Arrays.stream(roles).map(name -> new RoleDBO().setName(name)).collect(Collectors.toSet());
    }
}