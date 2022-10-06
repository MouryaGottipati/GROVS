package com.grovs.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class PaymentTypeConverter implements AttributeConverter<PaymentType, String>{

	@Override
	public String convertToDatabaseColumn(PaymentType paymentType) {
		// TODO Auto-generated method stub
		if(paymentType==null) {
			return null;
		}
		return paymentType.getType();
	}

	@Override
	public PaymentType convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if(dbData==null) {
			return null;
		}
		return Stream.of(PaymentType.values())
				.filter(p->p.getType().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	
}
