package authserver.api.users;

import authserver.api.users.dto.RegisterUserRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
//import authserver.config.JpaUserDetailsManager;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class UsersController {

    private final UserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @PostMapping("/register")
    public RedirectView registerUser(Model model,
                                     @ModelAttribute("registration") RegisterUserRequest registration,
                                     HttpServletResponse response) throws IOException {

        User.UserBuilder builder = User.builder().roles("USER");

        if (!userDetailsManager.userExists(registration.getUsername())) {

            LOGGER.debug("TEST LOG HERE");

            UserDetails user = builder
                    .username(registration.getUsername())
                    .password(passwordEncoder.encode(registration.getPassword()))
                    .build();

            userDetailsManager.createUser(user);

            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://127.0.0.1:8080/oauth2/authorization/gateway");

            return redirectView;
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://127.0.0.1:8080/register");

        return redirectView;
    }

}
