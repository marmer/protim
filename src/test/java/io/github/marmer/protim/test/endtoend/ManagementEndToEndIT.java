package io.github.marmer.protim.test.endtoend;

import io.github.marmer.protim.api.configuration.Role;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
public class ManagementEndToEndIT {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAtManagementApi_CallWithUser_ShouldBeForbidden()
            throws Exception {
        // Execution
        mockMvc.perform(
                get("/management")
                        .with(user("someone").roles(Role.USER.name())))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAtManagementApi_CallWithAdmin_ShouldWork()
            throws Exception {
        // Execution
        mockMvc.perform(
                get("/management")
                        .with(user("someone").roles(Role.ADMIN.name())))
                .andExpect(status().isOk());
    }

    @Test
    public void testAtManagementApi_CallWithAnonymous_ShouldWork()
            throws Exception {
        // Execution
        mockMvc.perform(
                get("/management")
                        .with(anonymous()))
                .andExpect(status().isUnauthorized());
    }
}
