package com.grovs.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleTypeConverter implements AttributeConverter<UserRole, String> {

	@Override
	public String convertToDatabaseColumn(UserRole userRole) {
		// TODO Auto-generated method stub
		if (userRole == null) {
			return null;
		}
		return userRole.getUserRole();
	}

	@Override
	public UserRole convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if (dbData == null) {
			return null;
		}
		return Stream.of(UserRole.values()).filter(c -> c.getUserRole().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
