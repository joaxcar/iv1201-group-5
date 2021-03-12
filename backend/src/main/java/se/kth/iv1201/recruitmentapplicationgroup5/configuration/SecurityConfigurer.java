package se.kth.iv1201.recruitmentapplicationgroup5.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import se.kth.iv1201.recruitmentapplicationgroup5.filter.JwtAuthFilter;
import se.kth.iv1201.recruitmentapplicationgroup5.service.AccountService;

/**
 * Class configuring java security, where to get account info and how to encode passwords.
 *
 */
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/v1/authenticate").permitAll()
			.antMatchers(HttpMethod.POST, "/api/v1/accounts").permitAll()
			.antMatchers("/api/v1/account/{id}/**")
	          .access("@userSecurity.hasUserId(authentication,#id)")
			.antMatchers(HttpMethod.GET, "/api/v1").permitAll()
			.antMatchers("/api/v1/**").authenticated()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	/**
	 * Configures Spring Boot with a default AuthenticationManager.
	 * 
	 * @return Default AuthenticationManager.
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	/**
	 * Configures Spring Boot to use a PasswordEncoder.
	 * 
	 * @return Chosen PasswordEncoder.
	 */
	@SuppressWarnings("deprecation") // Not really deprecated, you just aren't supposed to use it.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
