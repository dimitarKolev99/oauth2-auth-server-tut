package authserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AuthorityId.class)
public class Authority implements GrantedAuthority {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private String id;


	@Id
	private String username;

	@Id
	private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}

//	@NonNull
//	@Column(unique = true)
//	private String authority;
	
}
