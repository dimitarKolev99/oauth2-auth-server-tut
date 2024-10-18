package authserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AuthorityId.class)
public class Authority implements GrantedAuthority {
	@Id
	private String username;
	@Id
	@Column(unique = true)
	private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}
}
