package authserver.config;

import authserver.repository.ClientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.Set;
import java.util.UUID;

@Configuration
public class ClientsConfiguration {

    @Bean
    ApplicationRunner clientsRunner(JpaRegisteredClientRepository repository) {
        return args -> {
            String clientId = "client";
            if (repository.findByClientId(clientId) == null) {
                repository.save(
                        RegisteredClient
                                .withId(UUID.randomUUID().toString())
                                .clientId(clientId)
                                .clientSecret("{bcrypt}$2a$10$qRyOjYldTzpIdUqT.YF/meVKXkONN1mWa02onw/XPtn.Bc2UocJx6")
                                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(Set.of(
                                        AuthorizationGrantType.CLIENT_CREDENTIALS,
                                        AuthorizationGrantType.AUTHORIZATION_CODE,
                                        AuthorizationGrantType.REFRESH_TOKEN
                                )))
                                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/gateway")
                                .scopes(scopes -> scopes.addAll(Set.of("user.read", "user.write", OidcScopes.PROFILE, OidcScopes.OPENID)))
                                .build()
                );
            }
        };
    }

}
