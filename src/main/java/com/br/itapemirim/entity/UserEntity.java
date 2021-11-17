package com.br.itapemirim.entity;

import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER")
public class UserEntity implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "pass")
	private String pass;
	
	@Column(name = "email")
	private String email;
	
	 @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "user_roles",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	    private List<RoleEntity> roles;

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return roles;
	    }

	    @Override
	    public String getPassword() {
	        return pass;
	    }

	    @Override
	    public String getUsername() {
	        return login;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
	}