package io.github.marmer.protim.test.endtoend;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.persistence.relational.usermanagement.UserDBO;
import io.github.marmer.protim.test.TransactionlessTestEntityManager;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static io.github.marmer.protim.persistence.relational.usermanagement.RoleDBOTestdata.newRoleDBO;
import static io.github.marmer.protim.persistence.relational.usermanagement.UserDBOTestdata.newUserDBO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SelfInformationServiceEndToEndTest {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TransactionlessTestEntityManager transactionlessTestEntityManager;

    @Test
    @WithUserDetails("someone")
    public void testMe_CurrentlyAUserIsLoggedIn_ShouldReturnUserDetails()
            throws Exception {
        // Preparation
        final UserDBO user = transactionlessTestEntityManager.persist(newUserDBO().
                setEnabled(true).
                setRoles(Set.of(newRoleDBO().
                        setName(Role.USER))));

        // Execution
        mockMvc.perform(get("/api/v1/me"))

                // Assertion
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"username\": \"someone\",\n" +
                        "  \"roles\": [\n" +
                        "    \"USER\"\n" +
                        "  ]\n" +
                        "}"));
    }

    @Test
    @WithAnonymousUser()
    public void testMe_CurrentlyNoUserIsLoggedIn_ShouldReturnUserDetailsForAnonymous()
            throws Exception {
        // Preparation
        final UserDBO user = transactionlessTestEntityManager.persist(newUserDBO().
                setEnabled(true).
                setRoles(Set.of(newRoleDBO().
                        setName(Role.USER))));

        // Execution
        mockMvc.perform(get("/api/v1/me"))

                // Assertion
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"username\": \"someone\",\n" +
                        "  \"roles\": [\n" +
                        "    \"USER\"\n" +
                        "  ]\n" +
                        "}"));
    }

}
