package com.grovs.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class StatusTypeConverter implements AttributeConverter<Status, String>{

	@Override
	public String convertToDatabaseColumn(Status status) {
		// TODO Auto-generated method stub
		if(status==null) {
			return null;
		}
		return status.getStatus();
	}

	@Override
	public Status convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if(dbData==null) {
			return null;
		}
		return Stream.of(Status.values()).filter(c->c.getStatus().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
	
		
}
