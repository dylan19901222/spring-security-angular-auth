package www.com.spring.auth.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import www.com.spring.auth.dao.AuthUserDao;
import www.com.spring.auth.entity.AuthUser;

@RestController
@RequestMapping("api")
public class AuthoritieAPI {

	@Autowired
	private AuthUserDao authUserDao;

	// @PreAuthorize("hasRole('ADMIN')") 等於 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PreAuthorize("hasRole('ADMIN')") // 此註釋對應的是 WebSecurityConfig 中的 @prePostEnabled
	// 等同於 WebSecurityConfig 中的 @securedEnabled 使用 @Secured({"ROLE_ADMIN"})
	// 等同於 WebSecurityConfig 中的 @jsr250Enabled 使用 @RolesAllowed({"ADMIN"})
	@RequestMapping("/findAll")
	public @ResponseBody ResponseEntity<Object> findAll() {
		return new ResponseEntity<>(authUserDao.findAll(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')") // 此註釋對應的是 WebSecurityConfig 中的 @prePostEnabled
	// 等同於 WebSecurityConfig 中的 @securedEnabled 使用 @Secured({"ROLE_USER","ROLE_ADMIN"})
	// 等同於 WebSecurityConfig 中的 @jsr250Enabled 使用 @RolesAllowed({"ADMIN"})
	@RequestMapping(value = "/findSelf", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseEntity<Object> findSelf(@AuthenticationPrincipal AuthUser authUser) {
		return new ResponseEntity<>(authUserDao.findById(authUser.getId()).get(), HttpStatus.OK);
	}

	// @PreAuthorize 可使用 AND
	@PreAuthorize("hasRole('USER') AND hasRole('ADMIN')")
	@RequestMapping("/findUserName")
	public @ResponseBody ResponseEntity<Object> findUserName(@AuthenticationPrincipal AuthUser authUser) {
		return new ResponseEntity<>(authUser.getUsername(), HttpStatus.OK);
	}

}
