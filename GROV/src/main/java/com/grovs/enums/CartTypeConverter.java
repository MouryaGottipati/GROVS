package com.grovs.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class CartTypeConverter implements AttributeConverter<CartUserType, String>{

	@Override
	public String convertToDatabaseColumn(CartUserType cartUserType) {
		// TODO Auto-generated method stub
		if(cartUserType==null) {
			return null;
		}
		return cartUserType.getCartUserType();
	}

	@Override
	public CartUserType convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if(dbData==null) {
			return null;
		}
		return Stream.of(CartUserType.values())
				.filter(c->c.getCartUserType().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	
}
