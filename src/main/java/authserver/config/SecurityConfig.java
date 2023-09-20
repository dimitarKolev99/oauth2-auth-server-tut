package authserver.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
public class SecurityConfig {

	@Bean
	@Order(1)
	SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {
		
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		return http
//				.addFilterBefore(corsFilter(), SessionManagementFilter.class)
				//				.cors(Customizer.withDefaults())
				.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
				.oidc(withDefaults())
				.and()
				.exceptionHandling(e -> e
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
				.oauth2ResourceServer(resourceServer -> resourceServer.jwt(withDefaults()))
				.build();
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		return new CorsFilter();
//	}


//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:1234", "http://127.0.0.1:1234"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
//		configuration.setAllowCredentials(true);
//		configuration.setAllowedHeaders(Arrays.asList("*"));
//		configuration.setMaxAge(3600L);
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	private class CorsFilter implements Filter //javax.servlet.Filter
//	{
//
//		@Override
//		public void init(FilterConfig filterConfig) throws ServletException {
//
//		}
//
//		@Override
//		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//			HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
//			response.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
//			response.setHeader("Access-Control-Allow-Credentials", "true");
//			response.setHeader("Access-Control-Max-Age", "86400");
//			if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) servletRequest).getMethod())) {
//				response.setStatus(HttpServletResponse.SC_OK);
//			} else {
//				filterChain.doFilter(servletRequest, response);
//			}
//
//		}
//
//
//
//		@Override
//		public void destroy() {
//
//		}
//	}
	
	@Bean
	@Order(2)
	SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.formLogin(withDefaults())
				.authorizeHttpRequests(authorize ->authorize.anyRequest().authenticated())
				.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}
	
//	@Bean
//	OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
//		return context -> {
//			Authentication principal = context.getPrincipal();
//			if (context.getTokenType().getValue().equals("id_token")) {
//				context.getClaims().claim("Test", "Test Id Token");
//			}
//			if (context.getTokenType().getValue().equals("access_token")) {
//				context.getClaims().claim("Test", "Test Access Token");
//				Set<String> authorities = principal.getAuthorities().stream()
//                        .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
//                context.getClaims().claim("authorities", authorities)
//                        .claim("user", principal.getName());
//			}
//		};
//	}


	@Bean 
	JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}
	
	@Bean
	JWKSource<SecurityContext> jwkSource() {
		RSAKey rsaKey = generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}

	public static RSAKey generateRsa() {
		KeyPair keyPair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
	}

	static KeyPair generateRsaKey() {
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	}
}
