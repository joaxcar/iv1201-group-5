package se.kth.iv1201.recruitmentapplicationgroup5.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Class representing an account in the system. Has username, password and person.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.PROTECTED)
	private int id;
	
	@NotNull
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private Person person;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private Authority authority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		var grantedAuthority = new GrantedAuthority() {

			private static final long serialVersionUID = 1L;
			private Authority authority;
		
			@Override
			public String getAuthority() {
				return authority.getString();
			}
			
			private GrantedAuthority init(Authority authority) {
				this.authority = authority;
				return this;
			}
		}.init(authority);
		
		return Arrays.asList(grantedAuthority);
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
