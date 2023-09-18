package authserver.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import authserver.entity.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import authserver.entity.SecurityUser;
import authserver.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsManager implements UserDetailsManager {

	public static final Logger LOGGER = LoggerFactory.getLogger(JpaUserDetailsManager.class);

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityUser user = userRepository.findByUsername(username);

		LOGGER.debug("SecurityUser: {}", user);

		if (!user.getUsername().equals(username)) {
			LOGGER.debug("In exception");

			throw new UsernameNotFoundException("Access Denied");
		}
		Collection<GrantedAuthority> authoriies = new HashSet<>();
		user.getAuthorities().forEach(auth -> authoriies.add(new SimpleGrantedAuthority(auth.getAuthority())));
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), user.getAccountNonExpired(), 
				user.getCredentialsNonExpired(), user.getAccountNonLocked(), authoriies);
	}


	@Override
	public void createUser(UserDetails user) {
		Set<Authority> authorities = new HashSet<>();

		user.getAuthorities().forEach(auth -> authorities.add(new Authority(user.getUsername(), auth.getAuthority())));

		SecurityUser securityUser = new SecurityUser();
		securityUser.setAuthorities(authorities);
		securityUser.setUsername(user.getUsername());
		securityUser.setPassword(user.getPassword());
		securityUser.setEnabled(true);

		userRepository.save(securityUser);
	}

	@Override
	public void updateUser(UserDetails user) {
	}

	@Override
	public void deleteUser(String username) {
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
	}

	@Override
	public boolean userExists(String username) {
		SecurityUser user = userRepository.findByUsername(username);
		if (user != null && user.getUsername().equals(username)) {
			return true;
		}
		return false;
	}

}
