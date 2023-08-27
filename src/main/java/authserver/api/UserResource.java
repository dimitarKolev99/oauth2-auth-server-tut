package authserver.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import authserver.config.JpaUserDetailsManager;
import authserver.entity.SecurityUser;

@RestController
@RequiredArgsConstructor
public class UserResource {

    private final JpaUserDetailsManager userDetailsManager;

    @PostMapping("/signup")
    public void registerUser(@RequestBody SecurityUser securityUser) {
        userDetailsManager.createUser(securityUser);
    }

}
