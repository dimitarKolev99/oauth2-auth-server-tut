package authserver;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestUserDataFactory {
    private static final PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    public static UserDetails getTestUser() {
        User.UserBuilder builder = User.builder().passwordEncoder(delegatingPasswordEncoder::encode).roles("USER");

        return builder
                .username("User")
                .password("password")
                .disabled(false)
                .build();
    }
}
