package authserver.config;

import authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class UsersConfiguration {

    @Bean
    JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    ApplicationRunner usersRunner(UserDetailsManager userDetailsManager, UserRepository userRepository) {
        return args -> {
            User.UserBuilder builder = User.builder().roles("USER");

            Map<String, String> users = Map.of("User", "{bcrypt}$2a$10$BZ0eJi5yhG/LOHRyjunjCuDRCXWc2aqKbEQwS36kzlBWiSc.oEnle", "rwinch", "p@ssw0rd");
            users.forEach((username, password) -> {
                if (!userDetailsManager.userExists(username)) {
                    UserDetails user = builder
                            .username(username)
                            .password(password)
                            .build();

                    userDetailsManager.createUser(user);
                }
            });
        };
    }

}
