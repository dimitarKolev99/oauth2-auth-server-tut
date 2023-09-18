package authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import authserver.entity.SecurityUser;

public interface UserRepository extends JpaRepository<SecurityUser, String>{

	SecurityUser findByUsername(String username);
}
