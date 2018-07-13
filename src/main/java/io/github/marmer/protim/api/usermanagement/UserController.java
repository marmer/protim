package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {
    static final String BASE_URL = "/api/usermanagement/users";

    private final Converter<UserDTO, User> userConverter;
    private final UserService userService;
    private final Converter<User, UserDTO> userDtoConverter;

    @PutMapping
    public ResponseEntity putUser(@RequestBody final UserDTO userDto) throws URISyntaxException {
        final User user = userConverter.convert(userDto);
        userService.addUser(user);
        return ResponseEntity.created(userUriFor(user)).build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final String username) {
        return userService.getUser(username)
                .map(user1 -> ResponseEntity.ok(userDtoConverter.convert(user1)))
                .orElseGet(ResponseEntity.notFound()::build);
    }


    private URI userUriFor(final User user) throws URISyntaxException {
        final String userUri = BASE_URL + "/" + user.getUsername();
        return new URI(userUri);
    }
}
