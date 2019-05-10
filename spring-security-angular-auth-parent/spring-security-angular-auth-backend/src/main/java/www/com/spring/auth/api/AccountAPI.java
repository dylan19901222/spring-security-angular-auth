package www.com.spring.auth.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import www.com.spring.auth.dao.AuthUserDao;
import www.com.spring.auth.entity.AuthUser;


@RestController
@RequestMapping("api/account")
public class AccountAPI {

	@Autowired
	private AuthUserDao authUserDao;
	
	@RequestMapping("/login")
	public @ResponseBody ResponseEntity<Object> login(@AuthenticationPrincipal AuthUser authUser) {
		System.out.println(authUser);
		if (Optional.ofNullable(authUser).isPresent()) {
			authUser.setLastLogin(null);
			authUserDao.save(authUser);
			return new ResponseEntity<>(authUser, HttpStatus.OK);
		}
		return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);

	}

}
