package se.kth.iv1201.recruitmentapplicationgroup5.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Authority;

public class JwtUtil {
	private int TEN_HOURS_IN_MILLISECONDS = 36000000;
	private String SECRET_KEY = Base64.getEncoder().encodeToString("CHANGE_THIS_IN_PRODUCTION".getBytes());

	//TODO: Det här
	public String extractUsername(String token) {
		return null;
	}
	public Date extractExpiration(String token) {
		return null;
	}
	
	public Authority extractAuthority(String token) {
		return null;
	}
	
	public String generateToken(UserDetails account) {
		var claims = new HashMap<String, Object>();
		account.getAuthorities().stream().map(el -> claims.put("authority", el.toString()));
		return createToken(claims, account.getUsername());
	}
	
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
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
	}
}
