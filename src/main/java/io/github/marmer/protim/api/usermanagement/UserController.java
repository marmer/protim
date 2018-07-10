package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new URI(BASE_URL + "/" + user.getUsername());
    }
}
