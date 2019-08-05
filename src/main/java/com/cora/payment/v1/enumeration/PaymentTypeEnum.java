package com.cora.payment.v1.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PaymentTypeEnum {
	
	CREDIT_CARD("CREDIT_CARD"),
	BANK_SLIP("BANK_SLIP");
	
	@Getter
	private final String text;

}
