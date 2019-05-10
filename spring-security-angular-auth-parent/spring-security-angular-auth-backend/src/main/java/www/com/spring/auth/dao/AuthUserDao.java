package www.com.spring.auth.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import www.com.spring.auth.entity.AuthUser;


@Repository
public interface AuthUserDao extends CrudRepository<AuthUser, Long> {
	public Optional<AuthUser> findOneByUsername(String username);
	public Optional<AuthUser> findById(Long id);
	public List<AuthUser> findAll();
}
