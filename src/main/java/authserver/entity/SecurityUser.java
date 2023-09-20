package authserver.entity;

import java.util.Collection;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	
	@NonNull
	@Id
	@Column(unique = true)
	private String username;
	
	@NonNull
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	private Set<Authority> authorities;
//	private Collection<? extends GrantedAuthority> authorities;

//	private Boolean accountNonExpired;
//	private Boolean accountNonLocked;
//	private Boolean credentialsNonExpired;
	private Boolean enabled;

//	@Override
//	public boolean isAccountNonExpired() {
//		return accountNonExpired;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return accountNonLocked;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return credentialsNonExpired;
//	}

//	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
