package se.kth.iv1201.recruitmentapplicationgroup5.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Authority;

/*
 * Class for constructing and parsing JWT:s for use in spring security
 */
@Service
public class JwtUtil {
	private int TEN_HOURS_IN_MILLISECONDS = 36000000;
	private String SECRET_KEY = Base64.getEncoder().encodeToString("CHANGE_THIS_IN_PRODUCTION".getBytes());

	/**
	 * Extracts username out of a given token.
	 * 
	 * @param token JWT token to extract data from.
	 * 
	 * @return Username contained in token.
	 */
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	/**
	 * Extracts expiration time out of a given token.
	 * 
	 * @param token JWT token to extract data from.
	 * 
	 * @return Expiration time of token.
	 */
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	
	/**
	 * Extracts user authority out of a given token.
	 * 
	 * @param token JWT token to extract data from.
	 * 
	 * @return Authority contained in token.
	 */
	public Authority extractAuthority(String token) {
		return extractAllClaims(token).get("authority", Authority.class);
	}
	
	/**
	 * Generates a JWT containing data from a given account.
	 * 
	 * @param account Account who's data shall be stored in the token.
	 * 
	 * @return JWT token containing signed data from account.
	 */
	public String generateToken(UserDetails account) {
		var claims = new HashMap<String, Object>();
		account.getAuthorities().stream().map(authority -> claims.put("authority", authority.toString()));
		return createToken(claims, account.getUsername());
	}
	
	/**
	 * Validates that a given JWT given a user.
	 * 
	 * @param token JWT token to validate.
	 * @param userDetails User to validate.
	 * 
	 * @return true if token is valid, false otherwise.
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		var username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().addClaims(claims).setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + TEN_HOURS_IN_MILLISECONDS))
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
			.compact();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}
