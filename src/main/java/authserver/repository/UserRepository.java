package authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import authserver.entity.SecurityUser;

public interface UserRepository extends JpaRepository<SecurityUser, Integer>{

	SecurityUser findByUsername(String username);
}
