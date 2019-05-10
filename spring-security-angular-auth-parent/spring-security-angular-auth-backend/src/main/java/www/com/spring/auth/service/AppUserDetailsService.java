package www.com.spring.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import www.com.spring.auth.dao.AuthUserDao;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthUserDao authUserDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return authUserDao.findOneByUsername(username).get();
	}
}
