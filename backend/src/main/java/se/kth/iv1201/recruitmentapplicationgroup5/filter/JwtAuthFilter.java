package se.kth.iv1201.recruitmentapplicationgroup5.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import se.kth.iv1201.recruitmentapplicationgroup5.service.AccountService;
import se.kth.iv1201.recruitmentapplicationgroup5.util.JwtUtil;

/**
 * Filter checking for authorization cookie, and adding it to 
 *
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private JwtUtil util;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
	
		if(cookies != null) {
			String username = null;
			String jwt = Arrays.stream(cookies)
					.filter(cookie -> cookie.getName().equals("Authorization"))
					.map(cookie ->cookie.getValue())
					.findAny()
					.get();
			
			if(jwt != null) {
				username = util.extractUsername(jwt);
			}
			
			if(isValidUsernameAndIsNotAlreadyLoggedIn(username)) {
				UserDetails user = service.loadUserByUsername(username);
				if(util.validateToken(jwt, user)) {
					authenticateUser(user, request);
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	private boolean isValidUsernameAndIsNotAlreadyLoggedIn(String username) {
		return username != null && SecurityContextHolder.getContext().getAuthentication() == null;
	}
	
	private void authenticateUser(UserDetails user, HttpServletRequest request) {
		var usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
	}

}
