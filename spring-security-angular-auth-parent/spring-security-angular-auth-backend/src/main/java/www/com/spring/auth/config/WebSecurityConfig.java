package www.com.spring.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import www.com.spring.auth.service.AppUserDetailsService;

@Configurable
@EnableWebSecurity
// 權限控管分為securedEnabled、prePostEnabled三種可以挑其中一種
// 1.securedEnabled 對應註釋 @Secured ， 其對應Authority
// 2.prePostEnabled 對應註釋 @PreAuthorize ，可使用表達式使用Role 或 Authority
// 3.jsr250Enabled 對應註釋 @RolesAllowed ， 其對應Role
// securedEnabled、jsr250Enabled不能使用「角色AND角色」預設為OR
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AppUserDetailsService appUserDetailsService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;

	// passwordEncoder 請自行實作自己需要的密碼加密方式
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 可自行使用自己需要的 passwordEncoder
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 將前端靜態檔忽略掉
		web.ignoring().antMatchers("/*", "/assets/**");
	}

	protected void configure(HttpSecurity http) throws Exception {
		// Cross-Origin Resource Sharing (CORS)
		http.cors();

		// disabling the CSRF - Cross Site Request Forgery
		http.csrf().disable();

		http.authorizeRequests()
				// 想忽略的網址
				.antMatchers("/api/account/**", "/api/find/**").permitAll().anyRequest()
				.authenticated();

		// 設置不使用 session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/api/account/logout"))
				// 登出前調用方法
				// .addLogoutHandler(customLogoutHandler)
				// 登出後調用方法
				.logoutSuccessHandler(this::logoutSuccessHandler);

		// 當登入者沒權限或帳密錯誤調用方法
		http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
	}

	private void logoutSuccessHandler(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		response.setStatus(HttpStatus.OK.value());
		objectMapper.writeValue(response.getWriter(), "Bye!");
	}

	@Component
	public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

		@Autowired
		private ObjectMapper objectMapper;

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException, ServletException {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			objectMapper.writeValue(response.getWriter(), authException.getMessage());
		}
	}

}
