package authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import authserver.entity.SecurityUser;

import java.util.List;

public interface UserRepository extends JpaRepository<SecurityUser, String>{

	List<SecurityUser> findByUsername(String username);
}
