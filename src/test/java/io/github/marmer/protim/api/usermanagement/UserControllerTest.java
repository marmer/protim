package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.api.configuration.Role;
import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;

import static io.github.marmer.protim.api.configuration.Role.ADMIN;
import static io.github.marmer.protim.api.configuration.Role.USER;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@WithMockUser
public class UserControllerTest {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private Converter<UserDTO, User> userConverter;

    @Test
    public void testPutUser_UserGiven_ShouldSaveOrUpdateUser()
            throws Exception {
        // Preparation
        final User user = newUser().withUsername("Jim");
        final UserDTO userDto = new UserDTO()
                .setUsername("Jim")
                .setPassword("JTKirk")
                .setRoles(ADMIN, USER)
                .setEnabled(true);

        when(userConverter.convert(eq(userDto))).thenReturn(user);

        // Execution
        mockMvc.perform(put("/api/usermanagement/user")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"Jim\",\n" +
                        "  \"password\": \"JTKirk\",\n" +
                        "  \"roles\": [\n" +
                        "    \"" + ADMIN + "\",\n" +
                        "    \"" + USER + "\"\n" +
                        "  ],\n" +
                        "  \"enabled\": true\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/usermanagement/user/Jim"));

        // Assertion
        verify(userService).addUser(user);
    }

    private User newUser() {
        return new User("username", "password", new HashSet<>(asList(Role.USER)), true);
    }

}