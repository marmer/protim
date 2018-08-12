package io.github.marmer.protim.test.endtoend;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import io.github.marmer.protim.test.DbCleanupService;
import io.github.marmer.protim.test.TransactionlessTestEntityManager;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singleton;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationEndToEndIT {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    private final String restrictedInterface = "/management";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DbCleanupService dbCleanupService;
    @Autowired
    private TransactionlessTestEntityManager entityManager;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        dbCleanupService.clearAll();
    }

    @Test
    public void testAuthenticate_AuthenticationWithBasicAuthAndValidRoleForRestrictedInterface_ShouldGrandAccess()
            throws Exception {
        // Preparation
        userService.addUser(newUser()
                .withUsername("admin")
                .withPassword("admin123")
                .withEnabled(true)
                .withRoles(singleton(Role.ADMIN)));

        // Execution
        mockMvc.perform(get(restrictedInterface).with(httpBasic("admin", "admin123")))
                .andExpect(status().isOk());
    }

    private User newUser() {
        return User.builder().build();
    }

    @Test
    public void testAuthenticate_AuthenticationWithBasicAuthAndWrongRoleForRestrictedInterface_ShouldDenyAccess()
            throws Exception {
        // Preparation
        userService.addUser(newUser()
                .withUsername("user")
                .withPassword("user123")
                .withEnabled(true)
                .withRoles(singleton(Role.USER)));
        // Execution
        mockMvc.perform(get(restrictedInterface).with(httpBasic("user", "user123")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAuthenticate_AuthenticationWithWrongPasswordAuthAndValidRoleForRestrictedInterface_ShouldDenyAccess()
            throws Exception {
        // Preparation
        userService.addUser(newUser()
                .withUsername("user")
                .withPassword("user123")
                .withEnabled(true)
                .withRoles(singleton(Role.ADMIN)));

        // Execution
        mockMvc.perform(get(restrictedInterface).with(httpBasic("admin", "wrongPW")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAuthenticate_AuthenticationWithNotExistingUser_ShouldDenyAccess()
            throws Exception {
        // Preparation

        // Execution
        mockMvc.perform(get(restrictedInterface).with(httpBasic("admin", "admin123")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAuthenticate_AuthenticationWithDeactivatedUser_ShouldDenyAccess()
            throws Exception {
        // Preparation
        userService.addUser(newUser()
                .withUsername("user")
                .withPassword("user123")
                .withEnabled(false)
                .withRoles(singleton(Role.ADMIN)));

        // Execution
        mockMvc.perform(get(restrictedInterface).with(httpBasic("admin", "admin123")))
                .andExpect(status().isUnauthorized());
    }
}
