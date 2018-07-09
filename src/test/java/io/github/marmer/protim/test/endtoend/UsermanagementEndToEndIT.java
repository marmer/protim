package io.github.marmer.protim.test.endtoend;

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

import static io.github.marmer.protim.persistence.relational.usermanagement.RoleDBOMatcher.isRoleDBO;
import static io.github.marmer.protim.persistence.relational.usermanagement.UserDBOMatcher.isUserDBO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
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
    public void testPudUser_UserDoesNotExistYet_UserShouldExistNow()
            throws Exception {
        // Preparation
        final String username = "JTKirk";

        // Execution
        mockMvc.perform(
                put("/api/user")
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
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/user/{username}"));

        // Expectation
        assertThat(entityManager.find(UserDBO.class, username), isUserDBO()
                .withId(is(notNullValue()))
                .withUsername("Jim")
                .withPassword(username)
                .withRoles(contains(
                        isRoleDBO()
                                .withName("ADMIN"),
                        isRoleDBO()
                                .withName("USER")))
                .withEnabled(true)
                .withVersion(is(notNullValue()))
                .withCreatedBy(is(notNullValue()))
                .withCreatedDate(is(notNullValue()))
                .withLastModifiedBy(is(notNullValue()))
                .withLastModifiedDate(is(notNullValue()))
        );

    }


}