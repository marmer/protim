package io.github.marmer.protim.api.usermanagement;

import io.github.marmer.protim.api.ListDTO;
import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.usermanagement.User;
import io.github.marmer.protim.service.usermanagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {
    static final String BASE_URL = "/api/v1/usermanagement/users";

    private final Converter<UserDTO, User> userConverter;
    private final UserService userService;
    private final Converter<User, UserDTO> userDtoConverter;

    @PutMapping
    public ResponseEntity putUser(@RequestBody final UserDTO userDto) throws URISyntaxException {
        final User user = userConverter.convert(userDto);
        userService.addUser(user);
        return ResponseEntity.created(userUriFor(user)).build();
    }

    @GetMapping
    public ResponseEntity putUser() {
        return ResponseEntity.ok(
                new ListDTO()
                        .setEntries(
                                userService.getUsernames()
                                        .stream().
                                        map(new UserListEntryDTO()::setUsername)
                                        .collect(Collectors.toList())));
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
