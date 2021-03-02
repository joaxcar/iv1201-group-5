package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Authority;

/**
 * Class converting our enum Authority to string for persistence. used automagically by Spring.
 */
@Converter(autoApply = true)
public class AuthorityConverter implements AttributeConverter<Authority, String> {
	
	
	/**
	 * Converts enum Authority to database column type.
	 */
	@Override
	public String convertToDatabaseColumn(Authority authority) {
		if(authority == null) {
			return null;
		}
		return authority.getString();
	}

	
	/**
	 * Converts database column type to enum Authority.
	 */
	@Override
	public Authority convertToEntityAttribute(String dbData) {
		if(dbData == null) {
			return null;
		}
		return Stream.of(Authority.values())
				.filter(authType -> authType.getString().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
