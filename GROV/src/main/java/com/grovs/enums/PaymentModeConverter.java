package com.grovs.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class PaymentModeConverter implements AttributeConverter<PaymentMode, String>{

	@Override
	public String convertToDatabaseColumn(PaymentMode paymentMode) {
		// TODO Auto-generated method stub
		if(paymentMode==null) {
			return null;
		}
		return paymentMode.getPaymentMode();
	}

	@Override
	public PaymentMode convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if(dbData==null) {
			return null;
		}
		return Stream.of(PaymentMode.values())
				.filter(p->p.getPaymentMode().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
