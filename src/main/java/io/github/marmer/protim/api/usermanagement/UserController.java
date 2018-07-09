package io.github.marmer.protim.api.usermanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usermanagement/user")
@RequiredArgsConstructor()
public class UserController {

    @PutMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void putUser() {
    }
}
