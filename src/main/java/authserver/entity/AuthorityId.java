package authserver.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityId implements Serializable {
    private String username;
    private String authority;

    @Override
    public int hashCode() {
        return getAuthority() != null ? getAuthority().hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorityId)) return false;

        AuthorityId authorityId = (AuthorityId) o;

        return getAuthority() != null ? getAuthority().equals(authorityId.getAuthority()) : authorityId.getAuthority() == null;
    }
}
