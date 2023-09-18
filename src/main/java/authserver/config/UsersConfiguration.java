package authserver.config;

import authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class UsersConfiguration {

    @Bean
    ApplicationRunner usersRunner(PasswordEncoder passwordEncoder, JpaUserDetailsManager jpaUserDetailsManager) {
        return args -> {
            User.UserBuilder builder = User.builder().roles("USER").passwordEncoder(passwordEncoder::encode);

            Map<String, String> users = Map.of("User", "{bcrypt}$2a$10$qRyOjYldTzpIdUqT.YF/meVKXkONN1mWa02onw/XPtn.Bc2UocJx6", "rwinch", "p@ssw0rd");
            users.forEach((username, password) -> {
                if (!jpaUserDetailsManager.userExists(username)) {
                    User.UserBuilder user = builder
                            .username(username)
                            .password(password);

                    UserDetails userDetails = user.build();

                    jpaUserDetailsManager.createUser(userDetails);
                }
            });
        };
    }

}
