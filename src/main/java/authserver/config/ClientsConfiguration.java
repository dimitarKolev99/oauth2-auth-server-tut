package authserver.config;

import authserver.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class ClientsConfiguration {

    @Bean
    RegisteredClientRepository registeredClientRepository(JdbcTemplate template) {
        return new JdbcRegisteredClientRepository(template);
    }

    @Bean
    ApplicationRunner clientsRunner(RegisteredClientRepository repository) {
        return args -> {
            String clientId = "client";

            log.debug("RegisteredClientRepository: {}", repository.findByClientId(clientId));

            if (repository.findByClientId(clientId) == null) {

                log.debug("repository.findByClientId(clientId) == null");

                repository.save(
                        RegisteredClient
                                .withId(UUID.randomUUID().toString())
                                .clientId(clientId)
                                .clientSecret("{bcrypt}$2a$10$BZ0eJi5yhG/LOHRyjunjCuDRCXWc2aqKbEQwS36kzlBWiSc.oEnle")
                                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(Set.of(
                                        AuthorizationGrantType.CLIENT_CREDENTIALS,
                                        AuthorizationGrantType.AUTHORIZATION_CODE,
                                        AuthorizationGrantType.REFRESH_TOKEN
                                )))
                                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/gateway")
                                .scopes(scopes -> scopes.addAll(Set.of("user.read", "user.write", OidcScopes.PROFILE, OidcScopes.OPENID)))
                                .build()
                );
            }
        };
    }

}
