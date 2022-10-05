package com.grovs.enums;

import java.util.Arrays;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OffersTypeConverter implements AttributeConverter<Offers, String> {

	@Override
	public String convertToDatabaseColumn(Offers offer) {
		// TODO Auto-generated method stub
		if (offer == null) {
			return null;
		}
		return offer.getOffer();
	}

	@Override
	public Offers convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if (dbData == null) {
			return null;
		}
		return Stream.of(Offers.values()).filter(c -> c.getOffer().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
