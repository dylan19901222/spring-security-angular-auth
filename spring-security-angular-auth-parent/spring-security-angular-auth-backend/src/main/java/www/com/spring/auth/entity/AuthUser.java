package www.com.spring.auth.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "auth_user")
public class AuthUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4801817014533264000L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", length = 15, unique = true)
	private String username;

	@Column(name = "password", length = 128)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@JsonIgnore
	@Column(name = "role", length = 20)
	private String role;

	@JsonIgnore
	@Column(name = "is_enabled")
	private Boolean isEnabled;

	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "last_login")
	private Date lastLogin;

	@JsonIgnore
	@CreationTimestamp
	@Column(name = "date_joined")
	private Date dateJoined;

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		//將角色欄位取出並用「，」分隔
		for (String r : this.role.split("，")) {
			// 如果 Authority 前導字加入 ROLE_ 開頭會自行幫你加入 ROLE_XXX 中的XXX至角色中
			authorities.add(new SimpleGrantedAuthority(r));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
