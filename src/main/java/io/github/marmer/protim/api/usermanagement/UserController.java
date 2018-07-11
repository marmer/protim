package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {
    static final String BASE_URL = "/api/usermanagement/user";

    private final Converter<UserDTO, User> userConverter;
    private final UserService userService;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity putUser(@RequestBody final UserDTO userDto) throws URISyntaxException {
        final User user = userConverter.convert(userDto);
        userService.addUser(user);
        return ResponseEntity.created(userUriFor(user)).build();
    }

    private URI userUriFor(final User user) throws URISyntaxException {
        final String userUri = BASE_URL + "/" + user.getUsername();
        return new URI(userUri);
    }
}
